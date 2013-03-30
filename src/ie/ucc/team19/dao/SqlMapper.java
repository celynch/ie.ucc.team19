package ie.ucc.team19.dao;

import java.util.HashMap;

/**
 * Basic Object-relational Mapping. Used to get object type for prepared
 * statement parameter setting.
 * @author Cormac
 */
public class SqlMapper {
    private static HashMap<String, Integer> javaToSqlMap = new HashMap<String, Integer>();

    /**
     * Contructor initialises ORM.
     */
    public SqlMapper() {
        //javaToSqlMap.put("java.lang.String", java.sql.Types.CHAR);
        javaToSqlMap.put("java.math.BigDecimal", java.sql.Types.NUMERIC);
        javaToSqlMap.put("java.math.BigDecimal", java.sql.Types.DECIMAL);
        javaToSqlMap.put("java.lang.Boolean", java.sql.Types.TINYINT);
        javaToSqlMap.put("java.lang.Byte", java.sql.Types.TINYINT);
        javaToSqlMap.put("java.lang.Short", java.sql.Types.SMALLINT);
        javaToSqlMap.put("java.lang.Integer", java.sql.Types.INTEGER);
        javaToSqlMap.put("java.lang.Long", java.sql.Types.BIGINT);
        javaToSqlMap.put("java.lang.Float", java.sql.Types.REAL);
        javaToSqlMap.put("java.lang.Double", java.sql.Types.DOUBLE);
        javaToSqlMap.put("java.lang.Byte[]", java.sql.Types.BINARY);
        javaToSqlMap.put("java.sql.Date", java.sql.Types.DATE);
        javaToSqlMap.put("java.sql.Time", java.sql.Types.TIME);
    }

    /**
     * Maps an object to a corresponding java.sql.Type constant int field. 
     * @param obj - the Object to get the sql type of.
     * @return int java.sql.Type constant int field for object.
     */
    public static int getSqlTypeFromClass(Object obj) {
        String className = obj.getClass().getCanonicalName();
        int sqlType = -1;
        if(className != null) {
            sqlType = javaToSqlMap.get(className) == null ? -1 : javaToSqlMap.get(className);
        }
        return sqlType;
    }
}
