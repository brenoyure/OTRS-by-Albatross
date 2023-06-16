package br.albatross.otrs.domain.models.ticket;

import java.time.LocalDateTime;

import br.albatross.otrs.domain.models.Valid;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

/**
 * Representa o tipo do Serviço (do ticket) como: Estação de Trabalho, <br/>Instalação
 * de Softwares, Impressoras e Scanners, Garantia de Fabricante e etc.
 * 
 * @author breno.brito
 *
 */
@StaticMetamodel(value = Service.class)
public abstract class Service_ {

	public static volatile SingularAttribute<Service, Integer>        id;

	public static volatile SingularAttribute<Service, String>         name;

	public static volatile SingularAttribute<Service, String>         comments;

	public static volatile SingularAttribute<Service, LocalDateTime>  createTime;

	public static volatile SingularAttribute<Service, LocalDateTime>  changeTime;

	public static volatile SingularAttribute<Service, Valid>          valid;

}
