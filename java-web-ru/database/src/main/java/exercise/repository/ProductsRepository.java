package exercise.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import exercise.model.Product;

import java.sql.SQLException;
import java.sql.Statement;

public class ProductsRepository extends BaseRepository {

    // BEGIN
    public static void save(Product product) throws SQLException {
        String sql = "INSERT INTO goods (title, price) VALUES (?, ?)";
        try(var connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.executeUpdate();
            var generatedKey = preparedStatement.getGeneratedKeys();

            if (generatedKey.next()) {
                product.setId(generatedKey.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }

    }

    public static Optional<Product> find(Long id) throws SQLException {
        String sql = "SELECT * FROM goods WHERE id = ?";
        try(var connection = dataSource.getConnection(); var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var title = resultSet.getString("title");
                var price = resultSet.getInt("price");
                var product = new Product(title, price);
                product.setId(id);
                return Optional.of(product);
            }
            return Optional.empty();
        }
    }

    public static List<Product> getEntities() throws SQLException {
        String sql = "SELECT * FROM goods";
        try(var connection = dataSource.getConnection(); var statement = connection.prepareStatement(sql)) {
            var resultSet = statement.executeQuery();
            var result = new ArrayList<Product>();

            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var title = resultSet.getString("title");
                var price = resultSet.getInt("price");

                var product = new Product(title, price);
                product.setId(id);
                result.add(product);
            }
            return result;
        }
    }
    // END
}
