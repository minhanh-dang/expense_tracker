//package com.example.expensetracker.repository;
//
//import com.example.expensetracker.model.entity.Expenses;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.repository.query.FluentQuery;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Function;
//
//public class ExpensesImplement implements ExpenseRepository{
//
//    @Override
//    public Optional<List<Expenses>> findByUserId(Long id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<List<Expenses>> findByCategoryId(Long id) {
//        return Optional.empty();
//    }
//
//    @Autowired
//    private EntityManager entityManager;
//
//    @Override
//    public void saveExpense(Expenses expenses) {
//        Query query = entityManager.createNativeQuery("INSERT INTO expenses (description, price, status, paid_by, dateCreated, modifiedAt) VALUES (?, ?, ?)");
//
//        query.executeUpdate();
//
//    }
//
//    @Override
//    public List<Expenses> findAll() {
//        return null;
//    }
//
//    @Override
//    public List<Expenses> findAll(Sort sort) {
//        return null;
//    }
//
//    @Override
//    public Page<Expenses> findAll(Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public List<Expenses> findAllById(Iterable<Long> longs) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(Long aLong) {
//
//    }
//
//    @Override
//    public void delete(Expenses entity) {
//
//    }
//
//    @Override
//    public void deleteAllById(Iterable<? extends Long> longs) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends Expenses> entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//
//    @Override
//    public <S extends Expenses> S save(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends Expenses> List<S> saveAll(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public Optional<Expenses> findById(Long aLong) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(Long aLong) {
//        return false;
//    }
//
//    @Override
//    public void flush() {
//
//    }
//
//    @Override
//    public <S extends Expenses> S saveAndFlush(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends Expenses> List<S> saveAllAndFlush(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public void deleteAllInBatch(Iterable<Expenses> entities) {
//
//    }
//
//    @Override
//    public void deleteAllByIdInBatch(Iterable<Long> longs) {
//
//    }
//
//    @Override
//    public void deleteAllInBatch() {
//
//    }
//
//    @Override
//    public Expenses getOne(Long aLong) {
//        return null;
//    }
//
//    @Override
//    public Expenses getById(Long aLong) {
//        return null;
//    }
//
//    @Override
//    public Expenses getReferenceById(Long aLong) {
//        return null;
//    }
//
//    @Override
//    public <S extends Expenses> Optional<S> findOne(Example<S> example) {
//        return Optional.empty();
//    }
//
//    @Override
//    public <S extends Expenses> List<S> findAll(Example<S> example) {
//        return null;
//    }
//
//    @Override
//    public <S extends Expenses> List<S> findAll(Example<S> example, Sort sort) {
//        return null;
//    }
//
//    @Override
//    public <S extends Expenses> Page<S> findAll(Example<S> example, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public <S extends Expenses> long count(Example<S> example) {
//        return 0;
//    }
//
//    @Override
//    public <S extends Expenses> boolean exists(Example<S> example) {
//        return false;
//    }
//
//    @Override
//    public <S extends Expenses, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
//        return null;
//    }
//}
