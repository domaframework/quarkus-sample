package sample;

import io.agroal.api.AgroalDataSource;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.GreedyCacheSqlFileRepository;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.H2Dialect;

import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;

@ApplicationScoped
public class DbConfig implements Config {

  private final DataSource dataSource;

  private final Dialect dialect;

  private final GreedyCacheSqlFileRepository sqlFileRepository;

  public DbConfig(DataSource dataSource) {
    this.dataSource = dataSource;
    this.dialect = new H2Dialect();
    this.sqlFileRepository = new GreedyCacheSqlFileRepository();
  }

  @Override
  public Dialect getDialect() {
    return dialect;
  }

  @Override
  public DataSource getDataSource() {
    return dataSource;
  }

  @Override
  public GreedyCacheSqlFileRepository getSqlFileRepository() {
    return sqlFileRepository;
  }
}
