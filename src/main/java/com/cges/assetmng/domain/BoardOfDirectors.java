package com.cges.assetmng.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A BoardOfDirectors.
 */
@Entity
@Table(name = "board_of_directors")
public class BoardOfDirectors implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToMany
    @JoinTable(name = "board_of_directors_executive",
               joinColumns = @JoinColumn(name = "board_of_directors_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "executives_id", referencedColumnName = "id"))
    private Set<BoardMember> executives = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "board_of_directors_exec_assistent",
               joinColumns = @JoinColumn(name = "board_of_directors_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "exec_assistents_id", referencedColumnName = "id"))
    private Set<BoardMember> execAssistents = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "board_of_directors_member1",
               joinColumns = @JoinColumn(name = "board_of_directors_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "member1s_id", referencedColumnName = "id"))
    private Set<BoardMember> member1S = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "board_of_directors_member2",
               joinColumns = @JoinColumn(name = "board_of_directors_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "member2s_id", referencedColumnName = "id"))
    private Set<BoardMember> member2S = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "board_of_directors_member3",
               joinColumns = @JoinColumn(name = "board_of_directors_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "member3s_id", referencedColumnName = "id"))
    private Set<BoardMember> member3S = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "board_of_directors_member4",
               joinColumns = @JoinColumn(name = "board_of_directors_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "member4s_id", referencedColumnName = "id"))
    private Set<BoardMember> member4S = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "board_of_directors_member5",
               joinColumns = @JoinColumn(name = "board_of_directors_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "member5s_id", referencedColumnName = "id"))
    private Set<BoardMember> member5S = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public BoardOfDirectors startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BoardOfDirectors endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Set<BoardMember> getExecutives() {
        return executives;
    }

    public BoardOfDirectors executives(Set<BoardMember> boardMembers) {
        this.executives = boardMembers;
        return this;
    }

    public BoardOfDirectors addExecutive(BoardMember boardMember) {
        this.executives.add(boardMember);
        return this;
    }

    public BoardOfDirectors removeExecutive(BoardMember boardMember) {
        this.executives.remove(boardMember);
        return this;
    }

    public void setExecutives(Set<BoardMember> boardMembers) {
        this.executives = boardMembers;
    }

    public Set<BoardMember> getExecAssistents() {
        return execAssistents;
    }

    public BoardOfDirectors execAssistents(Set<BoardMember> boardMembers) {
        this.execAssistents = boardMembers;
        return this;
    }

    public BoardOfDirectors addExecAssistent(BoardMember boardMember) {
        this.execAssistents.add(boardMember);
        return this;
    }

    public BoardOfDirectors removeExecAssistent(BoardMember boardMember) {
        this.execAssistents.remove(boardMember);
        return this;
    }

    public void setExecAssistents(Set<BoardMember> boardMembers) {
        this.execAssistents = boardMembers;
    }

    public Set<BoardMember> getMember1S() {
        return member1S;
    }

    public BoardOfDirectors member1S(Set<BoardMember> boardMembers) {
        this.member1S = boardMembers;
        return this;
    }

    public BoardOfDirectors addMember1(BoardMember boardMember) {
        this.member1S.add(boardMember);
        return this;
    }

    public BoardOfDirectors removeMember1(BoardMember boardMember) {
        this.member1S.remove(boardMember);
        return this;
    }

    public void setMember1S(Set<BoardMember> boardMembers) {
        this.member1S = boardMembers;
    }

    public Set<BoardMember> getMember2S() {
        return member2S;
    }

    public BoardOfDirectors member2S(Set<BoardMember> boardMembers) {
        this.member2S = boardMembers;
        return this;
    }

    public BoardOfDirectors addMember2(BoardMember boardMember) {
        this.member2S.add(boardMember);
        return this;
    }

    public BoardOfDirectors removeMember2(BoardMember boardMember) {
        this.member2S.remove(boardMember);
        return this;
    }

    public void setMember2S(Set<BoardMember> boardMembers) {
        this.member2S = boardMembers;
    }

    public Set<BoardMember> getMember3S() {
        return member3S;
    }

    public BoardOfDirectors member3S(Set<BoardMember> boardMembers) {
        this.member3S = boardMembers;
        return this;
    }

    public BoardOfDirectors addMember3(BoardMember boardMember) {
        this.member3S.add(boardMember);
        return this;
    }

    public BoardOfDirectors removeMember3(BoardMember boardMember) {
        this.member3S.remove(boardMember);
        return this;
    }

    public void setMember3S(Set<BoardMember> boardMembers) {
        this.member3S = boardMembers;
    }

    public Set<BoardMember> getMember4S() {
        return member4S;
    }

    public BoardOfDirectors member4S(Set<BoardMember> boardMembers) {
        this.member4S = boardMembers;
        return this;
    }

    public BoardOfDirectors addMember4(BoardMember boardMember) {
        this.member4S.add(boardMember);
        return this;
    }

    public BoardOfDirectors removeMember4(BoardMember boardMember) {
        this.member4S.remove(boardMember);
        return this;
    }

    public void setMember4S(Set<BoardMember> boardMembers) {
        this.member4S = boardMembers;
    }

    public Set<BoardMember> getMember5S() {
        return member5S;
    }

    public BoardOfDirectors member5S(Set<BoardMember> boardMembers) {
        this.member5S = boardMembers;
        return this;
    }

    public BoardOfDirectors addMember5(BoardMember boardMember) {
        this.member5S.add(boardMember);
        return this;
    }

    public BoardOfDirectors removeMember5(BoardMember boardMember) {
        this.member5S.remove(boardMember);
        return this;
    }

    public void setMember5S(Set<BoardMember> boardMembers) {
        this.member5S = boardMembers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BoardOfDirectors boardOfDirectors = (BoardOfDirectors) o;
        if (boardOfDirectors.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), boardOfDirectors.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BoardOfDirectors{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
}
