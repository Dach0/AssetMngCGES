package com.cges.assetmng.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A BoardMember.
 */
@Entity
@Table(name = "board_member")
public class BoardMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "picture")
    private String picture;

    @ManyToOne
    @JsonIgnoreProperties("")
    private TitleInBoard boardTitle;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Qualification qualification;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public BoardMember name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public BoardMember lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public BoardMember phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public BoardMember email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public BoardMember picture(String picture) {
        this.picture = picture;
        return this;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public TitleInBoard getBoardTitle() {
        return boardTitle;
    }

    public BoardMember boardTitle(TitleInBoard titleInBoard) {
        this.boardTitle = titleInBoard;
        return this;
    }

    public void setBoardTitle(TitleInBoard titleInBoard) {
        this.boardTitle = titleInBoard;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public BoardMember qualification(Qualification qualification) {
        this.qualification = qualification;
        return this;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
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
        BoardMember boardMember = (BoardMember) o;
        if (boardMember.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), boardMember.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BoardMember{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", email='" + getEmail() + "'" +
            ", picture='" + getPicture() + "'" +
            "}";
    }
}
