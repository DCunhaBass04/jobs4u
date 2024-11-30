package jobs4u.core.jobopening.domain;

import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "RANK_POSITION")
public class Rank implements DomainEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer position;
    private Integer applicationNumber;
    public Rank(Integer position, Integer applicationNumber){
        Preconditions.noneNull(position, applicationNumber);

        this.position = position;
        this.applicationNumber = applicationNumber;
    }
    @Override
    public boolean sameAs(Object other) {
        return Objects.equals(this, other);
    }

    @Override
    public Long identity() {return id;}
    public String toString(){
        return String.format("#%d - Application no. %d", position, applicationNumber);
    }
}
