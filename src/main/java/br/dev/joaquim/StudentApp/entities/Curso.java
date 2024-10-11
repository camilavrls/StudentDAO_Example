package br.dev.joaquim.StudentApp.entities;

public class Curso {
  private int id;
  private String nome;
  private String horario;
  private String professor;

  public Curso() {
  }

  public Curso(int id, String nome, String horario, String professor) {
    this.id = id;
    this.nome = nome;
    this. horario = horario;
    this.professor = professor;
  }

  public int getId(){
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getHorario() {
    return horario;
  }

  public String getProfessor(){
    return professor;
  }

  public void setId(int id){
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setHorario(String horario){
    this.horario = horario;
  }

  public void setProfessor(String professor){
    this.professor = professor;
  }
}
