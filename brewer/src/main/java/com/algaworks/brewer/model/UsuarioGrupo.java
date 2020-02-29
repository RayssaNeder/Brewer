package com.algaworks.brewer.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="usuario_grupo")
public class UsuarioGrupo {
	
	
	@EmbeddedId
	private UsuarioGrupoId usuarioGrupoId;

	public UsuarioGrupoId getUsuarioGrupoId() {
		return usuarioGrupoId;
	}

	public void setUsuarioGrupoId(UsuarioGrupoId usuarioGrupoId) {
		this.usuarioGrupoId = usuarioGrupoId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuarioGrupoId == null) ? 0 : usuarioGrupoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioGrupo other = (UsuarioGrupo) obj;
		if (usuarioGrupoId == null) {
			if (other.usuarioGrupoId != null)
				return false;
		} else if (!usuarioGrupoId.equals(other.usuarioGrupoId))
			return false;
		return true;
	}
	
	

}
