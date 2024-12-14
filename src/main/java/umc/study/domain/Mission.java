package umc.study.domain;

import lombok.*;
import lombok.extern.java.Log;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.mapping.MemberMission;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer reward;

    private LocalDate deadline;

    private String missionSpec;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    public void setStore(Store store){
        if (this.reward != null)
            store.getMissionList().remove(this);
        this.store = store;
        store.getMissionList().add(this);
    }
}