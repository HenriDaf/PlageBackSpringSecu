package com.projet.plage.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.core.lang.NonNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="salage")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Salage {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
Long id;

@NonNull
byte [] sel;

@OneToOne(mappedBy = "salage", cascade = CascadeType.PERSIST)
@NonNull
@JsonIgnore
Utilisateur utilisateur;


	
}
