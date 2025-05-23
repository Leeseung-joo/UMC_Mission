package umc.study.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.study.common.BaseEntity;
import umc.study.mapping.FavoriteFood;
import umc.study.mapping.MissionHistory;
import umc.study.mapping.UserTerms;
import org.locationtech.jts.geom.Point;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "member_type") //이걸 해줌으로서 하위 클래스들의 이 컬럼이셍성됨, 더 명확해짐
@Getter
@Builder
@DynamicUpdate
@DynamicInsert //null 인경우에 쿼리를 보내지 않도록 함
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private String birthdate;

    @Column(length = 13)
    private String phoneNumber;

    @Column(nullable = false, columnDefinition = "POINT")
    private Point location;

    @Column(nullable = false)
    private LocalDateTime locationUpdatedAt;

    @Column(nullable = false)
    private Long completedMissionCount;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MissionHistory> memberMissonList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true) //연관 관계에서 제거된 자식 엔티티를 자동 삭제
    private List<RefreshToken> refreshTokenList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Notification> notificationList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoriteFood> favoriteFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTerms> userTermsList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegionReward> regionRewardList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "emd_areas_id", nullable = false)
    private EmdArea emdArea;











}
