package sistemarodrigo;

import java.util.List;

public class TurmaModel {
    private String numeroTurma;
    private DisciplinaModel disciplina;
    private List<AlunoModel> alunos;

    public TurmaModel(String numeroTurma, DisciplinaModel disciplina, List<AlunoModel> alunos) {
        this.numeroTurma = numeroTurma;
        this.disciplina = disciplina;
        this.alunos = alunos;
    }

    public String getNumeroTurma() {
        return numeroTurma;
    }

    public void setNumeroTurma(String numeroTurma) {
        this.numeroTurma = numeroTurma;
    }

    public DisciplinaModel getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(DisciplinaModel disciplina) {
        this.disciplina = disciplina;
    }

    public List<AlunoModel> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoModel> alunos) {
        this.alunos = alunos;
    }
}
