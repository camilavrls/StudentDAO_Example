package br.dev.joaquim.StudentApp.ihm;

import java.util.Scanner;

import br.dev.joaquim.StudentApp.dao.CursoDAO;
import br.dev.joaquim.StudentApp.entities.Curso;

public class CursoIHM {

    private CursoDAO cursoDAO;

    public CursoIHM(CursoDAO cursoDAO) {
        this.cursoDAO = cursoDAO;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            System.out.println("=== Curso Management Menu ===");
            System.out.println("1. Adicionar Curso");
            System.out.println("2. Listar todos os cursos");
            System.out.println("3. Listar um curso por id");
            System.out.println("4. Alterar horário de um curso");
            System.out.println("5. Alterar nome de um professor");
            System.out.println("6. Deletar curso");
            System.out.println("0. Exit");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addCurso(scanner);
                    break;
                case 2:
                    viewAllCursos();
                    break;
                case 3:
                    viewCursoById(scanner);
                    break;
                case 4:
                    updateHorarioCurso(scanner);
                    break;
                case 5:
                    updateNomeProfessor(scanner);
                    break;
                case 6:
                    deleteCurso(scanner);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    private void addCurso(Scanner scanner) {
        System.out.print("Digite o nome do curso: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o horário do curso: ");
        String horario = scanner.nextLine();
        System.out.print("Digite o nome do professor: ");
        String professor = scanner.nextLine();

        Curso curso = new Curso(0, nome, horario, professor);
        cursoDAO.createCurso(curso);
        System.out.println("Curso adicionado com sucesso.");
    }

    private void viewAllCursos() {
        System.out.println("=== Lista de Cursos ===");
        for (Curso curso : cursoDAO.findAll()) {
            System.out.println(curso);
        }
    }

    private void viewCursoById(Scanner scanner) {
        System.out.print("Digite o ID do curso: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Curso curso = cursoDAO.findById(id);
        if (curso != null) {
            System.out.println(curso);
        } else {
            System.out.println("Curso não encontrado.");
        }
    }

    private void updateHorarioCurso(Scanner scanner) {
        System.out.print("Digite o ID do curso para atualizar o horário: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Curso curso = cursoDAO.findById(id);
        if (curso == null) {
            System.out.println("Curso não encontrado.");
            return;
        }

        System.out.print("Digite o novo horário: ");
        String novoHorario = scanner.nextLine();
        cursoDAO.updateHorario(curso, novoHorario);
        System.out.println("Horário do curso atualizado com sucesso.");
    }

    private void updateNomeProfessor(Scanner scanner) {
        System.out.print("Digite o ID do curso para atualizar o nome do professor: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Curso curso = cursoDAO.findById(id);
        if (curso == null) {
            System.out.println("Curso não encontrado.");
            return;
        }

        System.out.print("Digite o novo nome do professor: ");
        String novoNomeProfessor = scanner.nextLine();
        cursoDAO.updateNomeProfessor(curso, novoNomeProfessor);
        System.out.println("Nome do professor atualizado com sucesso.");
    }

    private void deleteCurso(Scanner scanner) {
        System.out.print("Digite o ID do curso para deletar: ");
        int id = scanner.nextInt();
        cursoDAO.deleteCurso(id);
        System.out.println("Curso deletado com sucesso.");
    }
}
