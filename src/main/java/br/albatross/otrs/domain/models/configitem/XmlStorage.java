package br.albatross.otrs.domain.models.configitem;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Onde ficam armazenados os dados de um item de configuração como o cadastro de uma máquina.
 * 
 * @author breno.brito
 *
 */
@Entity @Table(name = "xml_storage")
@Getter @Setter
public class XmlStorage {

	@Id @GeneratedValue(strategy = IDENTITY)
	@Column(name = "xml_key", length = 250)
	private String xmlKey;

	@Column(length = 200, name = "xml_type", nullable = false)
	private String xmlType;

	@Column(length = 250, name = "xml_content_key", nullable = true)
	private String xmlContentKey;

	@Column(length = 200, name = "xml_content_value", nullable = true)
	private String xmlContentValue;
	
}
