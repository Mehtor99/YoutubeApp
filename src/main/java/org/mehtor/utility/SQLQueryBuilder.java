package org.mehtor.utility;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class SQLQueryBuilder {
	
	public static String generateInsert(Object entity, String tableName) {
		Class<?> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		StringBuilder columns = new StringBuilder();
		StringBuilder values = new StringBuilder();
		
		boolean firstField = true;
		for (Field field : fields) {
			field.setAccessible(true);
			if (!field.getName().equalsIgnoreCase("id")) {
				if (!firstField) {
					columns.append(", ");
					values.append(", ");
				} else {
					firstField = false;
				}
				columns.append(field.getName());
				try {
					Object value = field.get(entity);
					if (value == null) {
						values.append("NULL");
					}  else if (value instanceof String || value instanceof Date || value instanceof LocalDate || value instanceof Timestamp || value instanceof LocalDateTime || value instanceof Enum<?>) {
						values.append("'").append(value).append("'");
					} else {
						values.append(value);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ")";
	}
	
	public static String generateUpdate(Object entity, String tableName) {
		Class<?> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		StringBuilder setClause = new StringBuilder();
		Object idValue = null;
		
		for (Field field : fields) {
			field.setAccessible(true);
			if (field.getName().equalsIgnoreCase("id")) {
				try {
					idValue = field.get(entity);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			} else {
				try {
					Object value = field.get(entity);
					if (setClause.length() > 0) setClause.append(", ");
					setClause.append(field.getName()).append("=");
					if (value == null) {
						setClause.append("NULL");
					} else if (value instanceof String || value instanceof Date || value instanceof LocalDate || value instanceof Timestamp) {
						setClause.append("'").append(value).append("'");
					} else {
						setClause.append(value);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		if (idValue == null) {
			throw new IllegalArgumentException("ID yok.");
		}
		
		return "UPDATE " + tableName + " SET " + setClause + " WHERE id=" + idValue;
	}
	
	public static String generateDelete(Class<?> entityClass, String tableName, Object id) {
		String idColumnName = "id";
		String sql = "DELETE FROM " + tableName + " WHERE " + idColumnName + " = '" + id + "'";
		return sql;
	}
	
	public static <T> List<T> generateList(Class<T> entityClass, String tableName, ResultSet resultSet) {
		List<T> resultList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				T entity = entityClass.getDeclaredConstructor().newInstance();
				Class<?> currentClass = entityClass;
				while (currentClass != null) {
					for (Field field : currentClass.getDeclaredFields()) {
						field.setAccessible(true);
						String fieldName = field.getName();
						Object value = resultSet.getObject(fieldName);
						
						if (value != null) {
							// LocalDate türü için kontrol
							if (field.getType().equals(LocalDate.class) && value instanceof java.sql.Date) {
								field.set(entity, ((java.sql.Date) value).toLocalDate());
							}
							// LocalDateTime türü için kontrol
							else if (field.getType().equals(LocalDateTime.class) && value instanceof Timestamp) {
								field.set(entity, ((Timestamp) value).toLocalDateTime());
							}
							// LocalTime türü için kontrol
							else if (field.getType().equals(LocalTime.class) && value instanceof java.sql.Time) {
								field.set(entity, ((java.sql.Time) value).toLocalTime());
							}
							// BigDecimal türü için kontrol (money veya decimal türleri)
							else if (field.getType().equals(BigDecimal.class) && (value instanceof Double || value instanceof Float || value instanceof Long)) {
								field.set(entity, BigDecimal.valueOf(((Number) value).doubleValue()));
							}
							// UUID türü için kontrol
							else if (field.getType().equals(UUID.class) && value instanceof UUID) {
								field.set(entity, value);
							}
							// Boolean türü için kontrol
							else if (field.getType().equals(Boolean.class) && value instanceof Boolean) {
								field.set(entity, value);
							}
							else {
								field.set(entity, value);
							}
						}
					}
					currentClass = currentClass.getSuperclass();
				}
				
				resultList.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	public static <T> Optional<T> findById(Class<T> entityClass, String tableName, Object id, ResultSet resultSet) {
		String idColumnName = "id";
		String sql = "SELECT * FROM " + tableName + " WHERE " + idColumnName + " = '" + id + "'";
		try {
			if (resultSet.next()) {
				T entity = entityClass.getDeclaredConstructor().newInstance();
				Class<?> currentClass = entityClass;
				while (currentClass != null) {
					for (Field field : currentClass.getDeclaredFields()) {
						field.setAccessible(true);
						String fieldName = field.getName();
						Object value = resultSet.getObject(fieldName);
						
						if (value != null) {
							// LocalDate türü için kontrol
							if (field.getType().equals(LocalDate.class) && value instanceof java.sql.Date) {
								field.set(entity, ((java.sql.Date) value).toLocalDate());
							}
							// LocalDateTime türü için kontrol
							else if (field.getType().equals(LocalDateTime.class) && value instanceof Timestamp) {
								field.set(entity, ((Timestamp) value).toLocalDateTime());
							}
							// LocalTime türü için kontrol
							else if (field.getType().equals(LocalTime.class) && value instanceof java.sql.Time) {
								field.set(entity, ((java.sql.Time) value).toLocalTime());
							}
							// BigDecimal türü için kontrol (money veya decimal türleri)
							else if (field.getType().equals(BigDecimal.class) && (value instanceof Double || value instanceof Float || value instanceof Long)) {
								field.set(entity, BigDecimal.valueOf(((Number) value).doubleValue()));
							}
							// UUID türü için kontrol
							else if (field.getType().equals(UUID.class) && value instanceof UUID) {
								field.set(entity, value);
							}
							// Boolean türü için kontrol
							else if (field.getType().equals(Boolean.class) && value instanceof Boolean) {
								field.set(entity, value);
							}

							// Diğer türler için
							else {
								field.set(entity, value);
							}
						}
					}
					currentClass = currentClass.getSuperclass();
				}
				
				return Optional.of(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Optional.empty();
	}
	
}