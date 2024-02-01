package com.products.backend.infra.util.specification;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import java.util.Collection;

public class BaseSpecification {

    private static Path getPath(Path root, String field) {
        String[] fields = StringUtils.split(field, ".");
        Path path = root;
        for (String f : fields) {
            path = path.get(f);
        }
        return path;
    }

    public static <T> Specification<T> empty() {
        return Specification.where(null);
    }

    public static <T> Specification<T> fieldEquals(String field, Object value) {
        if (field == null)
            return Specification.where(null);
        if (value == null)
            return fieldIsNull(field);
        return (root, query, cb) -> {
            return cb.equal(getPath(root, field), value);
        };
    }

    public static <T> Specification<T> fieldNotEquals(String field, Object value) {
        if (field == null)
            return Specification.where(null);
        if (value == null)
            return fieldIsNotNull(field);
        return (root, query, cb) -> {
            return cb.notEqual(getPath(root, field), value);
        };
    }

    public static <T> Specification<T> fieldEqualsIgnoreCase(String field, Object value) {
        if (field == null || value == null)
            return Specification.where(null);
        return (root, query, cb) -> {
            return cb.equal(cb.lower(getPath(root, field)), value.toString().toLowerCase());
        };
    }

    public static <T> Specification<T> fieldNotEqualsIgnoreCase(String field, Object value) {
        if (field == null || value == null)
            return Specification.where(null);
        return (root, query, cb) -> {
            return cb.notEqual(cb.lower(getPath(root, field)), value.toString().toLowerCase());
        };
    }

    public static <T> Specification<T> fieldContains(String field, Object value) {
        if (field == null || value == null)
            return Specification.where(null);
        return (root, query, cb) -> {
            return cb.like(getPath(root, field), "%" + value + "%");
        };
    }

    public static <T> Specification<T> fieldNotContains(String field, Object value) {
        if (field == null || value == null)
            return Specification.where(null);
        return (root, query, cb) -> {
            return cb.notLike(getPath(root, field), "%" + value + "%");
        };
    }

    public static <T> Specification<T> fieldContainsIgnoreCase(String field, Object value) {
        if (field == null || value == null)
            return Specification.where(null);
        return (root, query, cb) -> {
            return cb.like(cb.lower(getPath(root, field)), "%" + value.toString().toLowerCase() + "%");
        };
    }

    public static <T> Specification<T> fieldNotContainsIgnoreCase(String field, Object value) {
        if (field == null || value == null)
            return Specification.where(null);
        return (root, query, cb) -> {
            return cb.notLike(cb.lower(getPath(root, field)), "%" + value.toString().toLowerCase() + "%");
        };
    }

    public static <T> Specification<T> fieldStartWidth(String field, Object value) {
        if (field == null || value == null)
            return Specification.where(null);
        return (root, query, cb) -> {
            return cb.like(getPath(root, field), "%" + value);
        };
    }

    public static <T> Specification<T> fieldNotStartWidth(String field, Object value) {
        if (field == null || value == null)
            return Specification.where(null);
        return (root, query, cb) -> {
            return cb.notLike(getPath(root, field), "%" + value);
        };
    }

    public static <T> Specification<T> fieldEndWidth(String field, Object value) {
        if (field == null || value == null)
            return Specification.where(null);
        return (root, query, cb) -> {
            return cb.like(getPath(root, field), value + "%");
        };
    }

    public static <T> Specification<T> fieldNotEndWidth(String field, Object value) {
        if (field == null || value == null)
            return Specification.where(null);
        return (root, query, cb) -> {
            return cb.notLike(getPath(root, field), value + "%");
        };
    }

    public static <T> Specification<T> fieldIsNull(String field) {
        if (field == null)
            return Specification.where(null);
        return (root, query, cb) -> {
            return cb.isNull(getPath(root, field));
        };
    }

    public static <T> Specification<T> fieldIsNotNull(String field) {
        if (field == null)
            return Specification.where(null);
        return (root, query, cb) -> {
            return cb.isNotNull(getPath(root, field));
        };
    }

    public static <T> Specification<T> fieldIsEmpty(String field) {
        if (field == null)
            return Specification.where(null);
        return (root, query, cb) -> {
            return cb.isEmpty(getPath(root, field));
        };
    }

    public static <T> Specification<T> fieldIsNotEmpty(String field) {
        if (field == null)
            return Specification.where(null);
        return (root, query, cb) -> {
            return cb.isNotEmpty(getPath(root, field));
        };
    }

    public static <T> Specification<T> fieldIn(String field, Collection<?> value) {
        if (field == null || value == null || value.isEmpty())
            return Specification.where(null);
        return (root, query, cb) -> {
            return cb.in(getPath(root, field)).value(value);
        };
    }

    public static <T> Specification<T> fieldNotIn(String field, Collection<?> value) {
        return Specification.not(fieldIn(field, value));
    }

    public static <T> Specification<T> fieldLessThanOrEqualTo(String field, Comparable value) {
        if (field == null || value == null)
            return Specification.where(null);
        return (root, query, cb) -> {
            return cb.lessThanOrEqualTo(getPath(root, field), value);
        };
    }

    public static <T> Specification<T> fieldGreaterThan(String field, Comparable value) {
        if (field == null || value == null)
            return Specification.where(null);
        return (root, query, cb) -> {
            return cb.greaterThan(getPath(root, field), value);
        };
    }

    public static <T> Specification<T> fieldGreaterThanOrEqualTo(String field, Comparable value) {
        if (field == null || value == null)
            return Specification.where(null);
        return (root, query, cb) -> {
            return cb.greaterThanOrEqualTo(getPath(root, field), value);
        };
    }
}
