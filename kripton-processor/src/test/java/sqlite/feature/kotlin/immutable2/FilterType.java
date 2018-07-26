package sqlite.feature.kotlin.immutable2;

/**
 * foreach filter definition, there is a SQL where condition to add to statement obtained by getSql method.
 */
public enum FilterType {
    ALL(""),
    UNREAD("read=0"),
    READ("read=1");

    FilterType(String sql) {
        this.sql=sql;
    }

    String sql;

    public String getSql() {
        return sql;
    }
}
