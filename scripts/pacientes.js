let pacientes = JSON.parse(localStorage.getItem("pacientes")) || [];

function cadastrar(mensagem) {
  let oCpf = document.getElementById("cpf");
  let oNome = document.getElementById("nome");
  let oData = document.getElementById("data");
  let oEmail = document.getElementById("email");
  let oTel = document.getElementById("telefone");
  let oSexo = document.getElementById("sexo");
  let oPlano = document.getElementById("plano");

  let cpf = oCpf.value;
  let nome = oNome.value;
  let data = oData.value;
  let email = oEmail.value;
  let telefone = oTel.value;
  let sexo = oSexo.value;
  let plano = oPlano.value;

  if (!cpf || !nome || !data || !email || !telefone || !sexo || !plano) {
    alert("Todos os campos são obrigatórios.");
    return;
  }

  const cpfPattern = /^(\d{3}\.){2}\d{3}-\d{2}$/;
  if (!cpfPattern.test(cpf)) {
    alert("CPF inválido. Deve ser: XXX.XXX.XXX-XX");
    return;
  }

  const phonePattern = /^[1-9]{2}[0-9]{6}$/;
  if (!phonePattern.test(telefone)) {
    alert("Telefone inválido. Deve estar no formato XXXXXXXX");
    return;
  }

  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailPattern.test(email)) {
    alert("Email inválido.");
    return;
  }

  let flag = false;
  for (let i = 0; i < pacientes.length; i++) {
    if (pacientes[i].cpf === cpf) {
      flag = true;
      break;
    }
  }

  if (flag) {
    alert("O cadastro deste CPF não foi possível, pois já está cadastrado.");
  } else {
    let oCadastro = {
      cpf: cpf,
      nome: nome,
      data: data,
      email: email,
      telefone: telefone,
      sexo: sexo,
      plano: plano,
    };

    pacientes.unshift(oCadastro);
    localStorage.setItem("pacientes", JSON.stringify(pacientes));
    oCpf.value = "";
    oNome.value = "";
    oData.value = "";
    oEmail.value = "";
    oTel.value = "";
    oSexo.value = "";
    oPlano.value = "";
    alert(mensagem ?? "Cadastro efetuado com sucesso");
  }
}

document.addEventListener("DOMContentLoaded", (event) => {
  const pacienteParaEditar = JSON.parse(
    localStorage.getItem("pacienteParaEditar")
  );
  if (pacienteParaEditar) {
    document.getElementById("cpf").value = pacienteParaEditar.cpf;
    document.getElementById("nome").value = pacienteParaEditar.nome;
    document.getElementById("data").value = pacienteParaEditar.data;
    document.getElementById("email").value = pacienteParaEditar.email;
    document.getElementById("telefone").value = pacienteParaEditar.telefone;
    document.getElementById("sexo").value = pacienteParaEditar.sexo;
    document.getElementById("plano").value = pacienteParaEditar.plano;

    document.getElementById("cadastrar").style.display = "none";
    document.getElementById("AlterarPaciente").style.display = "inline-block";
  }
});

function AlterarPaciente() {
  const cpf = document.getElementById("cpf").value;
  const pacienteParaEditar = JSON.parse(
    localStorage.getItem("pacienteParaEditar")
  );

  const pacientesAtualizados = pacientes.filter(
    (paciente) => paciente.cpf !== pacienteParaEditar.cpf
  );

  let flag = false;
  for (let i = 0; i < pacientesAtualizados.length; i++) {
    if (pacientesAtualizados[i].cpf === cpf) {
      flag = true;
      break;
    }
  }

  if (flag) {
    alert("Não é possível atualizar o cadastro. CPF já está em uso.");
  } else {
    let pacienteAtualizado = {
      cpf: cpf,
      nome: document.getElementById("nome").value,
      data: document.getElementById("data").value,
      email: document.getElementById("email").value,
      telefone: document.getElementById("telefone").value,
      sexo: document.getElementById("sexo").value,
      plano: document.getElementById("plano").value,
    };

    pacientes = pacientesAtualizados;
    pacientes.unshift(pacienteAtualizado);
    localStorage.setItem("pacientes", JSON.stringify(pacientes));

    localStorage.removeItem("pacienteParaEditar");

    document.getElementById("cpf").value = "";
    document.getElementById("nome").value = "";
    document.getElementById("data").value = "";
    document.getElementById("email").value = "";
    document.getElementById("telefone").value = "";
    document.getElementById("sexo").value = "";
    document.getElementById("plano").value = "";

    alert("Cadastro atualizado com sucesso");

    document.getElementById("cadastrar").style.display = "inline-block";
    document.getElementById("AlterarPaciente").style.display = "none";
  }
}
