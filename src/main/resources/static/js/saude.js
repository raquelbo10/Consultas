
$(document).ready(function () {
$("#dados-usuario").hide();
    function carregarUsuario(id) {
        $.ajax({
            url: `/usuario/{id}`,
            type: "GET",
            success: function (usuario) {

                $("#nome").text(usuario.nome),
                        $("#sobrenome").text(usuario.sobrenome),
                        $("#genero").text(usuario.genero),
                        $("#cpf").text(usuario.cpf),
                        $("#user").text(usuario.user),
                        $("#tel").text(usuario.tel),
                        $("#usuarioId").text(usuario.usuarioId);

            },
            error: function (error) {
                console.log("Erro ao carregar usuário: ", error);
                alert("Erro ao carregar os dados do usuário. Tente novamente.");
            }
        });
    }

    function listarUsuarios() {
        $.ajax({
            url: 'http://localhost:8080/usuario/listar-todos',
            type: "GET",
            success: function (usuarios) {
                console.log(usuarios);
                var tabelaUsuarios = $("#tabelaUsuarios tbody");
                tabelaUsuarios.empty();
                usuarios.forEach(function (usuario) {
                    var linha = $("<tr>");
                    linha.append($("<td>").text(usuario.id));
                    linha.append($("<td>").text(usuario.nome));
                    linha.append($("<td>").text(usuario.sobrenome));
                    linha.append($("<td>").text(usuario.cpf));
                    linha.append($("<td>").text(usuario.genero));
                    linha.append($("<td>").text(usuario.tel));
                    linha.append($("<td>").text(usuario.user));
                    var atualizarLink = $("<a href='#'>").text("Atualizar").data("id", usuario.id);
                    var excluirLink = $("<a href='#'>").text("Excluir").data("id", usuario.id);



                    atualizarLink.click(function (event) {
                        event.preventDefault();
                        var id = $(this).data("id");
                        carregarUsuario(id);
                        $("#dados-usuario").show(); 

                    });
                   



                    excluirLink.click(function (event) {
                        event.preventDefault();
                        var id = $(this).data("id");
                        deletarUsuario(id);
                    });


                    linha.append($("<td>").append(atualizarLink).append(" ").append(excluirLink));
                    tabelaUsuarios.append(linha);
                });

            },
            error: function (error) {
                alert("Erro ao listar usuários: " + error);
            }
        });
    }

    function adicionarUsuario(usuario) {
        $.ajax({
            url: "/usuario/adicionar",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(usuario),
            success: function (novoUsuario) {
                console.log("Usuário adicionado: ", novoUsuario);

                listarUsuarios();
                limparForm();
                alert("Cadastro realizado com sucesso!");
                window.location.href = "/Login";
            },
            error: function (error) {
                console.log("Erro ao adicionar usuário: " + error);
            }
        });
    }
    function autenticarUsuario(user, senha) {

        $.ajax({
            url: "/usuario/buscar?user=" + user + "&senha=" + senha,
            type: "GET",
            success: function (usuarioEncontrado) {
                console.log("Usuário Encontrado: ", usuarioEncontrado);

                window.location.href = "/Agenda";
                $("#idUsuario").val(usuarioEncontrado.id);
            },
            error: function (error) {
                console.log("Erro ao buscar usuário: ", error);
                alert("Usuário ou senha inválidos.");
            }
        });
    }
    function buscarUsuarioPorCpf(cpf) {

        $.ajax({
            url: "/usuario/pesquisar-cpf?cpf=" + cpf,
            type: "GET",
            success: function (usuarioEncontrado) {
                console.log("Usuário Encontrado: ", usuarioEncontrado);
                var tabela = $("<table>").addClass("table table-bordered");
                var linha = $("<tr>");

                
                linha.append($("<td>").text(usuarioEncontrado.nome));
                linha.append($("<td>").text(usuarioEncontrado.sobrenome));
                linha.append($("<td>").text(usuarioEncontrado.cpf));
                linha.append($("<td>").text(usuarioEncontrado.genero));
                linha.append($("<td>").text(usuarioEncontrado.tel));
                linha.append($("<td>").text(usuarioEncontrado.user));
                tabela.append(linha);
                $("#dadosUsuario").html(tabela);
                $("#usuarioId").val(usuarioEncontrado.id);
            },
            error: function (error) {
                console.log("Erro ao buscar usuário: ", error);
                alert("Usuário ou senha inválidos.");
            }
        });
    }

    function atualizarUsuario(id, usuario) {
        $.ajax({
            url: `/usuario/atualizar/${id}`,
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(usuario),
            success: function (usuarioAtualizado) {
                console.log("Usuário atualizado: ", usuarioAtualizado);
                $("#dados-usuario").hide(); 
                listarUsuarios();
                alert("Usuário atualizado com sucesso!");
            },
            error: function (error) {
                console.error("Erro ao atualizar usuário: ", error);
                alert("Erro: " + JSON.stringify(error.responseText));
            }
        });
    }

    function deletarUsuario(id) {
        $.ajax({
            url: `/usuario/excluir/${id}`,
            type: "DELETE",
            success: function () {
                console.log("Usuário deletado.");
                listarUsuarios();
            },
            error: function (error) {
                console.log("Erro ao deletar usuário: ", error);
            }
        });
    }


    $("#cadastrarbtn").click(function (event) {
        event.preventDefault();

        const usuario = {
            nome: $("#nome").val(),
            sobrenome: $("#sobrenome").val(),
            genero:$("input[name='genero']:checked").val() ,
            cpf: $("#cpf").val(),
            user: $("#user").val(),
            senha: $("#senha").val(),
            tel: $("#tel").val()
        };
        adicionarUsuario(usuario);
        limparForm();
    });

    $("#entrarbtn").click(function (event) {
        event.preventDefault();

        var user = $("#user").val();
        var senha = $("#senha").val();

        autenticarUsuario(user, senha);



    });



    $("#btnAtualizar").click(function (event) {
        event.preventDefault();
        const id = parseInt($("#usuario-id").val());
        const usuario = {
            
            nome: $("#usuario-nome").val(),
            sobrenome: $("#usuario-sobrenome").val(),
            genero: $("#usuariogenero").val(),
            cpf: $("#usuario-cpf").val(),
            user: $("#usuario-user").val(),
            senha: $("#usuario-senha").val(),
            tel: $("#usuario-tel").val()
        };


        atualizarUsuario(id, usuario);
    });

    function limparForm() {
        $("#nome").val("");
        $("#sobrenome").val("");
        $("#genero").val("");
        $("#cpf").val("");
        $("#user").val("");
        $("#senha").val("");
        $("#tel").val("");
        $("#usuarioId").val("");

    }
    $("#pesquisarbtn").click(function (event) {
        event.preventDefault();
        var cpf = $("#cpfUsuario").val();
        buscarUsuarioPorCpf(cpf);



    });
    
        
        

    
 function carregarUsuario(id) {
    $.ajax({
        url: `/usuario/buscar/${id}`,  
        type: "GET",                 
        success: function (usuario) {
            
           $("#usuario-id").val(usuario.id);
        $("#usuario-nome").val(usuario.nome);
        $("#usuario-sobrenome").val(usuario.sobrenome);
        $("#usuario-genero").val(usuario.genero);
        $("#usuario-cpf").val(usuario.cpf);
        $("#usuario-user").val(usuario.user);
        $("#usuario-senha").val(usuario.senha);
        $("#usuario-tel").val(usuario.tel);
            },
            error: function (error) {
                console.log("Erro ao carregar usuário: ", error);
                alert("Erro ao carregar os dados do usuário. Tente novamente.");  
            }
        });
    }

    
   
    function listarConsultas() {
        $.ajax({
            url: 'http://localhost:8080/consulta/listar-todas',
            type: "GET",
            success: function (consultas) {
                console.log(consultas);
                var tabelaConsultas = $("#tabelaConsultas tbody");
                tabelaConsultas.empty();
                consultas.forEach(function (consulta) {
                   
                    
                    var linha = $("<tr>");
                    linha.append($("<td>").text(consulta.id));
                    linha.append($("<td>").text(consulta.data));
                    linha.append($("<td>").text(consulta.hora));
                    linha.append($("<td>").text(consulta.tipo));
                    linha.append($("<td>").text(consulta.profissional));
                    linha.append($("<td>").text(consulta.usuario.nome + "" + consulta.usuario.sobrenome));




                    var excluirLink = $("<a href='#'>").text("Excluir").data("id", consulta.id);




                    excluirLink.click(function (event) {
                        event.preventDefault();
                        var id = $(this).data("id");
                        deletarConsulta(id);
                    });


                    linha.append($("<td>").append(excluirLink));
                    tabelaConsultas.append(linha);


                });



            }
        });
    }
    function adicionarConsulta(consulta) {
        $.ajax({
            url: "/consulta/adicionar",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(consulta),
            success: function (novaConsulta) {
                console.log("Consulta adicionado: ", novaConsulta);
                listarConsultas();
                limparFormConsulta();
              alert(
    "Consulta agendada com sucesso!\n" +
    "Usuário: " + novaConsulta.usuario.nome + " " + novaConsulta.usuario.sobrenome + "\n" +
    "Data: " + novaConsulta.data + "\n" +
    "Hora: " + novaConsulta.hora + "\n" +
    "Profissional: " + novaConsulta.profissional
);

                window.location.href = "/Login";
            },
            error: function (xhr, status, error) {
                let errorMessage;
                if (xhr.responseJSON && xhr.responseJSON.message) {
                    errorMessage = xhr.responseJSON.message;
                } else {
                    errorMessage = `Erro inesperado: ${xhr.status} - ${xhr.statusText}`;
                }
                console.error("Erro ao salvar consulta:", errorMessage);
                alert(`Erro ao salvar consulta: ${errorMessage}`);
            }
        });
    }

    function deletarConsulta(id) {
        $.ajax({
            url: "/consulta/excluir/" + id,
            type: "DELETE",
            success: function () {
                console.log("Consulta deletada.");
                listarConsultas();
            },
            error: function (error) {
                console.log("Erro ao deletar consulta: ", error);
            }
        });
    }
    function limparFormConsulta() {
        $("#usuarioId").val("");
        $("#hora").val("Selecione");
        $("#data").val("");
        $("#tipo").val("Selecione");
        $("#profissional").val("Selecione");
    }

    $("#agendarbtn").click(function (event) {
        event.preventDefault();
        var usuarioId = $("#usuarioId").val();
        const consulta = {
            usuario: {id: $("#usuarioId").val()},
            hora: $("#hora").val(),
            data: $("#data").val(),
            tipo: $("#tipo").val(),
            profissional: $("#profissional").val()

        };
        adicionarConsulta(consulta);
        limparFormConsulta();
    });
    listarUsuarios();
    listarConsultas();

});



