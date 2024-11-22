$(document).ready(function () {
    $("#cadastrarbtn").click(function () {

        if (
                !validaNome() ||
                !validaSobrenome() ||
                !validaSenha() ||
                !validaCpf() ||
                !validaTel() ||
                !validaUser() ||
                !validaGenero()
                ) {
            event.preventDefault();
        }
    });

    $("#agendarbtn").click(function () {
        if (!validaIdUsuario() ||
                !validaTipoConsulta() ||
                !validaProfissional() ||
                !validaHora() ||
                !validaData()) {
            event.preventDefault();
        }



    });


    function validaNome() {
        const nome = $("#nome").val();
        if (nome.length < 2) {
            alert("O nome deve ter pelo menos 2 caracteres.");
            return false;
        } else {
            return true;
        }
    }
    function validaSobrenome() {
        const sobrenome = $("#sobrenome").val();
        if (sobrenome.length < 2) {
            alert("O sobrenome deve ter pelo menos 2 caracteres.");

            return false;
        } else {
            return true;
        }
    }
    function validaUser() {
        const user = $("#user").val();
        if (user.length < 4) {
            alert("Usuário deve ter pelo menos 4 caracteres.");
            return false;
        } else {
            return true;
        }
    }
    function validaSenha() {
        const senha = $("#senha").val();
        const senha1 = $("#senha1").val();
        if (senha.length < 6 && senha !== senha1) {
            alert("Senha deve ter pelo menos 6 caracteres e deve ser igual a confirmação de senha.");
            return false;
        } else {
            return true;
        }
    }

    function validaCpf() {
        const cpfRegex = /^\d{3}\.\d{3}\.\d{3}-\d{2}$/;
        const cpf = $("#cpf").val();
        if (!cpfRegex.test(cpf)) {
            alert("O CPF deve estar no formato indicado.");

            return false;
        } else {
            return true;
        }
    }
    function validaTel() {
        const telRegex = /^\(\d{2}\)\d{4,5}-\d{4}$/;
        const tel = $("#tel").val();

        if (!telRegex.test(tel)) {
            alert("O número de telefone deve estar no formato indicado.");

            return false;
        } else {
            return true;
        }
    }

    function validaGenero() {
        const genero = $("input[name='genero']:checked").val();

        if (!genero) {
            alert("Você deve selecionar um gênero.");
            return false;
        } else {
            return true;
        }
    }







    function validaIdUsuario() {
        var idUsuario = $('#idUsuario').val();

        if (!idUsuario) {
            alert("Por favor, insira um código de usuário válido.");
            return false;
        } else {
            return true;
        }
    }
    function validaProfissional() {
        var profissional = $('#profissional').val();
        if (profissional === "Selecione") {
            alert("Por favor, selecione um profissional.");
            return false;
        } else {
            return true;
        }
    }
    function validaTipoConsulta() {
        var tipo = $('#tipo').val();
        if (tipo === "Selecione") {
            alert("Por favor, selecione o tipo de consulta.");
            return false;
        } else {
            return true;
        }
    }
    function validaData() {
        var data = $('#data').val();
        if (!data) {
            alert("Por favor, selecione uma data.");
        } else {
            var dataAtual = new Date().toISOString().split('T')[0];
            if (data < dataAtual) {
                alert("A data deve ser no futuro.");
                return false;
            } else {
                return true;
            }
        }
    }
    function validaHora() {
        var hora = $('#hora').val();
        if (hora === "Selecione") {
            alert("Por favor, selecione um horário.");
            return false;
        } else {
            return true;
        }
    }


});



