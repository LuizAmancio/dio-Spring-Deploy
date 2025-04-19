package com.estudoSpring.deployNuvem.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private String name;
    
    @OneToOne(cascade = CascadeType.ALL) // 1 para 1 e quando usuario deletado a conta vai junto
    private Conta conta;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Cartao cartao;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // 1 para N e toda vez que busca usuario ja traz as features
    private List<Feature> features;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Noticia> noticias;

    public Usuario(String name, Conta conta, Cartao cartao) {
        this.name = name;
        this.conta = conta;
        this.cartao = cartao;
    }

    
}
