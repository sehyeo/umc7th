package umc.study.domain;

import lombok.*;
import lombok.extern.java.Log;
import umc.study.domain.common.BaseEntity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Region extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<Store> storeList = new ArrayList<>();

    public void setStore(Store store){
        if (this.id != null)
            storeList.remove(store);
        this.storeList.add(store);
    }
}