package br.com.nutriclinic.api.model;

public class AuthTokenModel {
	private Long id;
	private String token;
	private String nomeUsuario;
	private String perfilUsuario;
	private Long idPaciente;
	private Long idPlanoAlimentar;
	private String avatar;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(String perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

	public Long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Long getIdPlanoAlimentar() {
		return idPlanoAlimentar;
	}

	public void setIdPlanoAlimentar(Long idPlanoAlimentar) {
		this.idPlanoAlimentar = idPlanoAlimentar;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
