package com.test.hotelsearch.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.test.hotelsearch.constants.HotelConstants;

public class HotelSpecification<T> implements Specification<T> {

	private SearchCriteria criteria;

	public HotelSpecification(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (criteria.getValue() != null) {
			return compareValue(criteriaBuilder, root.<String>get(criteria.getKey()), criteria.getValue().toString());
		} else {
			return compareValue(criteriaBuilder, root.<String>get(criteria.getKey()), null);
		}
	}

	private <Y extends Comparable<? super Y>> Predicate compareValue(CriteriaBuilder builder,
			Expression<? extends Y> value1, Y value2) {
		if (criteria.getOperation().equalsIgnoreCase(HotelConstants.GREATER_THAN)) {
			return builder.greaterThan(value1, value2);
		} else if (criteria.getOperation().equalsIgnoreCase(HotelConstants.LESS_THAN)) {
			return builder.lessThan(value1, value2);
		} else if (criteria.getOperation().equalsIgnoreCase(HotelConstants.GREATER_THAN_EQUAL)) {
			return builder.greaterThanOrEqualTo(value1, value2);
		} else if (criteria.getOperation().equalsIgnoreCase(HotelConstants.LESS_THAN_EQUAL)) {
			return builder.lessThanOrEqualTo(value1, value2);
		} else if (criteria.getOperation().equalsIgnoreCase(HotelConstants.EQUAL)) {
			return builder.equal(value1, value2);
		} else if (criteria.getOperation().equalsIgnoreCase(HotelConstants.LIKE)) {
			return builder.like((Expression<String>) value1, "%" + value2.toString() + "%");
		} else if (criteria.getOperation().equalsIgnoreCase(HotelConstants.NOT_LIKE)) {
			return builder.notLike((Expression<String>) value1, "%" + value2.toString() + "%");
		} else if (criteria.getOperation().equalsIgnoreCase(HotelConstants.NULL)) {
			return builder.isNull(value1);
		}
		return null;
	}

	/*
	 * private Predicate getPredicate(CriteriaBuilder criteriaBuilder, Path<Object>
	 * root, String key) { if (criteria.getValue() != null) { return
	 * compareValue(criteriaBuilder, root.<String>get(key),
	 * criteria.getValue().toString()); } else { return
	 * compareValue(criteriaBuilder, root.<String>get(key), null); } }
	 */
}
