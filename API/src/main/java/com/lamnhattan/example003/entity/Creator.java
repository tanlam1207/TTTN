// package com.lamnhattan.example003.entity;

// import java.io.Serializable;
// import java.util.UUID;

// import com.lamnhattan.example003.entity.ProductAttribute.ProductAttributeID;

// import jakarta.persistence.*;
// import lombok.*;

// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Entity
// @Table(name = "creator")
// public abstract class Creator {
//     @EmbeddedId
//     private CreatorId id;

//     @ManyToOne
//     @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = false)
//     private StaffAccount createdBy;

//     @ManyToOne
//     @JoinColumn(name = "updated_by", referencedColumnName = "id")
//     private StaffAccount updatedBy;

//     @Embeddable
//     public static class CreatorId implements Serializable {
//         @Column(name = "creator_id")
//         private UUID creatorId;
//     }
// }
