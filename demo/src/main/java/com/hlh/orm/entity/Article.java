package com.hlh.orm.entity;

import com.hlh.orm.listener.DataBaseAuditListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(DataBaseAuditListener.class)
public class Article implements Serializable {
    @Serial
    private static final long serialVersionUID = -2631847427215494114L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String author;

    private String title;

    private String content;

    private String thumbnail;

    @Column(name = "create_time", updatable = false)
    private Date createTime;



}


