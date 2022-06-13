package kr.co.memberservice.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_member")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;

    @Column(unique = true, name = "identity", nullable = false, length = 100)
    private String identity;
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Column(name = "name")
    private String name;

    @Builder
    public MemberEntity(String identity, String password, String name) {
        this.identity = identity;
        this.password = password;
        this.name = name;
    }
}
