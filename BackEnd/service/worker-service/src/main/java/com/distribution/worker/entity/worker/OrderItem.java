package com.distribution.worker.entity.worker;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(catalog = "worker_db", name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "item_id")
    private Item product;

    private int qty;

    @ManyToOne
    @JoinColumn(name = "task_record_id")
    private TaskRecord taskRecord;


}
