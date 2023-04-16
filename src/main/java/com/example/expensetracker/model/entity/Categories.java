package com.example.expensetracker.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Categories {
    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy="category")
    private List<Expenses> expensesList;
}
