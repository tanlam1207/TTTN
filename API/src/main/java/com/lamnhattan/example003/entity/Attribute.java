    package com.lamnhattan.example003.entity;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attributes")
public class Attribute {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 11)
    private Long id;

    @Column(name = "attribute_name", nullable = false, length = 255)
    private String attribute_name;

    @Column(name = "created_at", nullable = false, length = 255)
    private Date created_at;

    @Column(name = "updated_at", nullable = false, length = 255)
    private Date updated_at;

        @ManyToOne
        @JoinColumn(name = "created_by")
        private StaffAccount createdBy;

        @ManyToOne
        @JoinColumn(name = "updated_by")
        private StaffAccount updatedBy;

}