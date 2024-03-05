//package com.group.libraryapp.repository.fruit;
//
//import com.group.libraryapp.domain.homework.Fruit;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Repository
//public class FruitMySQLRepository implements FruitRepositoryInterface{
//    private final JdbcTemplate jdbcTemplate;
//
//    public FruitMySQLRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public void save(String name, LocalDate warehousingDate, long price) {
//        String sql = "INSERT INTO fruit (name,warehousing_date,price) VALUES (?,?,?)";
//        jdbcTemplate.update(sql, name, warehousingDate, price);
//    }
//
//    @Override
//    public void updateSellStatus(long id) {
//        String sql = "UPDATE fruit SET sell_status = 'SELLING' WHERE id = ?";
//        jdbcTemplate.update(sql, id);
//    }
//
//    @Override
//    public List<Fruit> getSalesInfo(String fruitName) {
//        String sql = "SELECT * FROM fruit WHERE name = ?";
//        return jdbcTemplate.query(sql
//                , (rs, rowNum) -> new Fruit(rs.getString("name"),
//                        rs.getLong("price"),
//                        null,
//                        rs.getString("sell_status"))
//                , fruitName);
//    }
//}