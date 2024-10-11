package br.dev.joaquim.StudentApp.dao;

import br.dev.joaquim.StudentApp.entities.Curso;
import java.util.List;

public interface CursoDAO {
  public boolean createCurso(int id, String nome, String horario, String professor);

  public List<Curso> findAll();

  public Curso findById(int id);

  public boolean updateHorario(Curso curso, String novoHorario);

  public boolean updateNomeProfessor(Curso curso, String novoNomeProfessor);

  public boolean deleteCurso(int id);
}
