package br.dev.joaquim.StudentApp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.dev.joaquim.StudentApp.entities.Curso;

public class H2CursoDAO implements CursoDAO {

    private Connection connection;
    private String url = "jdbc:h2:file:~/data/cursos;";
    private String user = "root";
    private String password = "root";

    public H2CursoDAO() {
        connect();
        createTableIfNotExists();
    }

    private void connect() {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            this.connection = null;
            System.out.println("Problema ao conectar no banco de dados");
            ex.printStackTrace();
        }
    }

    private void createTableIfNotExists() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS cursos(" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nome VARCHAR(256) NOT NULL, " +
                    "horario VARCHAR(256), " +
                    "professor VARCHAR(256));";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Problema ao criar a tabela");
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            System.out.println("Problema ao criar a tabela (sem conexao)");
        }
    }

    @Override
    public List<Curso> findAll() {
        try {
            String sql = "SELECT * FROM cursos";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            List<Curso> cursos = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String horario = rs.getString("horario");
                String professor = rs.getString("professor");
                Curso curso = new Curso(id, nome, horario, professor);
                cursos.add(curso);
            }

            return cursos;

        } catch (SQLException ex) {
            System.out.println("Problema ao buscar cursos");
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            System.out.println("Problema ao buscar cursos (sem conexao)");
        }

        return new ArrayList<>();
    }

    @Override
    public Curso findById(int id) {
        try {
            String sql = "SELECT * FROM cursos WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int Id = rs.getInt("id");
                String name = rs.getString("nome");
                String horario = rs.getString("horario");
                String professor = rs.getString("professor");
                return new Curso(Id, name, horario, professor);
            }
        } catch (SQLException ex) {
            System.out.println("Problema ao buscar matéria pelo nome");
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            System.out.println("Problema ao buscar matéria pelo nome (sem conexao)");
        }

        return null;
    }

    @Override
    public boolean updateHorario(Curso curso, String novoHorario) {
        try {
            String sql = "UPDATE cursos SET horario = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(2, curso.getId());
            stmt.setString(1, novoHorario);
            stmt.executeUpdate();

            return true;

        } catch (SQLException ex) {
            System.out.println("Problema ao atualizar curso");
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            System.out.println("Problema ao atualizar curso (sem conexao)");
        }

        return false;
    }

    @Override
    public boolean updateNomeProfessor(Curso curso, String novoNomeProfessor) {
        try {
            String sql = "UPDATE cursos SET professor = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(2, curso.getId());
            stmt.setString(1, novoNomeProfessor);
            stmt.executeUpdate();

            return true;

        } catch (SQLException ex) {
            System.out.println("Problema ao atualizar curso");
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            System.out.println("Problema ao atualizar curso (sem conexao)");
        }

        return false;
    }

    @Override
    public boolean deleteCurso(int id) {
        try {
            String sql = "DELETE FROM cursos WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            return true;

        } catch (SQLException ex) {
            System.out.println("Problema ao apagar curso");
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            System.out.println("Problema ao apagar curso (sem conexao)");
        }

        return false;
    }

    public boolean createCurso(Curso curso) {
        try {
            String sql = "INSERT INTO cursos (nome, horario, professor) VALUES(?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getHorario());
            stmt.setString(3, curso.getProfessor());
            stmt.executeUpdate();

            return true;

        } catch (SQLException ex) {
            System.out.println("Problema ao criar curso");
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            System.out.println("Problema ao criar curso (sem conexao)");
        }

        return false;
    }
}
