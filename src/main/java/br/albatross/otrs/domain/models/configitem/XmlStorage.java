package br.albatross.otrs.domain.models.configitem;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Onde ficam armazenados os dados de um item de configuração como o cadastro de uma máquina.
 * 
 * @author breno.brito
 *
 */
@Table(name = "xml_storage")
@Getter @Setter
public class XmlStorage {

	@Column(length = 200, name = "xml_type", nullable = false)
	private String xmlType;

	@Column(length = 200, name = "xml_content_value", nullable = true)
	private String xmlContentValue;

}
