# FinalProj
Projeto final da disciplina de BackEnd com o professor Marcelo de Souza 

*Endpoints de Autenticação e Registro*
Este controlador gerencia os processos de autenticação e registro de usuários no sistema. Abaixo estão os endpoints implementados e suas descrições, juntamente com as permissões necessárias para acessar as rotas relacionadas à entidade "médicos".

1. Login
Rota: POST /auth/login

Descrição: Autentica um usuário com as credenciais fornecidas e gera um token JWT se as credenciais forem válidas.
Parâmetros de Entrada:
AuthenticationDTO (JSON):
login (String): O nome de usuário do usuário.
password (String): A senha do usuário.
Resposta:
- Em caso de sucesso: Um token JWT é retornado em formato JSON.
- Em caso de falha: Um erro 401 é retornado com a mensagem "Credenciais inválidas".
Permissões: Nenhuma permissão especial é necessária para fazer login, qualquer usuário registrado pode acessar este endpoint.
2. Registro
Rota: POST /auth/register

Descrição: Registra um novo usuário no sistema.
Parâmetros de Entrada:
RegisterDTO (JSON):
login (String): O nome de usuário desejado.
password (String): A senha do usuário.
role (Enum UserRole): O papel do usuário, que pode ser USER ou ADMIN.
Resposta:
Em caso de sucesso: Retorna a mensagem "Usuário cadastrado com sucesso".
Em caso de falha: Retorna um erro 400 se o nome de usuário já estiver em uso.
Permissões: Nenhuma permissão especial é necessária para se registrar.


Aqui está a documentação dos endpoints da classe MedicoController, incluindo uma relação com o processo de autenticação gerido pelo AuthenticationController:

  *Endpoints de Médicos*
A classe MedicoController gerencia as operações CRUD para a entidade Medico. Abaixo estão os endpoints implementados, suas descrições e as permissões necessárias para acessá-los. As permissões de acesso são controladas com base nos papéis de usuário (roles) definidos no AuthenticationController.

1. Listar Todos os Médicos
Rota: GET /api/medicos

Descrição: Retorna uma lista de todos os médicos cadastrados.
Resposta:
Em caso de sucesso: Retorna uma lista de objetos MedicoResponse com status 200 (OK).

[
  {
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "nome": "Dr. João Silva",
    "especialidade": "Cardiologia",
    "crm": "123456-SP"
  },
  {
    "id": "123e4567-e89b-12d3-a456-426614174001",
    "nome": "Dra. Maria Souza",
    "especialidade": "Pediatria",
    "crm": "654321-SP"
  }
]
Permissões: Usuários com ROLE_USER ou ROLE_ADMIN podem acessar este endpoint.
Para acessar a rota, o usuário deve estar autenticado e incluir um token JWT no cabeçalho da requisição:
bash
Copiar código
Authorization: Bearer <token>
2. Obter Médico por ID
Rota: GET /api/medicos/{id}

Descrição: Retorna os detalhes de um médico específico com base no seu ID.

Parâmetros:

id (UUID): O identificador único do médico.
Resposta:

Em caso de sucesso: Retorna o objeto MedicoResponse do médico solicitado com status 200 (OK).

{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "nome": "Dr. João Silva",
  "especialidade": "Cardiologia",
  "crm": "123456-SP"
}
Em caso de falha: Retorna 404 se o médico com o ID fornecido não for encontrado.
Permissões: Usuários com ROLE_USER ou ROLE_ADMIN podem acessar este endpoint.

Autenticação por token JWT é necessária, similar ao endpoint anterior.
3. Adicionar Novo Médico
Rota: POST /api/medicos

Descrição: Adiciona um novo médico ao sistema.
Parâmetros:
MedicoRequest (JSON): Dados do médico a ser criado.
{
  "nome": "Dr. Carlos Oliveira",
  "especialidade": "Ortopedia",
  "crm": "789123-SP"
}
Resposta:
Em caso de sucesso: Retorna o objeto MedicoResponse criado com status 201 (Created).
{
  "id": "123e4567-e89b-12d3-a456-426614174002",
  "nome": "Dr. Carlos Oliveira",
  "especialidade": "Ortopedia",
  "crm": "789123-SP"
}
Permissões: Apenas usuários com ROLE_ADMIN podem acessar este endpoint para adicionar novos médicos.
Um token JWT com permissões de administrador deve ser incluído no cabeçalho da requisição.
4. Atualizar Médico por ID
Rota: PUT /api/medicos/{id}

Descrição: Atualiza os dados de um médico existente com base no seu ID.

Parâmetros:

id (UUID): O identificador único do médico.
MedicoRequest (JSON): Os novos dados do médico.
{
  "nome": "Dr. Carlos Oliveira",
  "especialidade": "Traumatologia",
  "crm": "789123-SP"
}
Resposta:

Em caso de sucesso: Retorna o objeto MedicoResponse atualizado com status 200 (OK).
{
  "id": "123e4567-e89b-12d3-a456-426614174002",
  "nome": "Dr. Carlos Oliveira",
  "especialidade": "Traumatologia",
  "crm": "789123-SP"
}
Permissões: Apenas usuários com ROLE_ADMIN podem acessar este endpoint para atualizar médicos.

Autenticação JWT com permissões de administrador é necessária.
5. Excluir Médico por ID
Rota: DELETE /api/medicos/{id}

Descrição: Exclui um médico existente do sistema com base no seu ID.
Parâmetros:
id (UUID): O identificador único do médico a ser excluído.
Resposta:
Em caso de sucesso: Retorna status 204 (No Content) sem corpo de resposta.
Permissões: Apenas usuários com ROLE_ADMIN podem acessar este endpoint para excluir médicos.
Um token JWT válido com permissões de administrador é necessário.
Relação com o AuthenticationController
O controlador AuthenticationController é responsável por gerenciar a autenticação e a geração de tokens JWT. A relação entre os endpoints do AuthenticationController e do MedicoController é a seguinte:

Login (POST /auth/login): O token JWT gerado após o login é necessário para acessar qualquer rota do MedicoController.
Registro (POST /auth/register): Apenas usuários com o papel apropriado (como ADMIN) poderão acessar os endpoints que requerem privilégios administrativos, como a criação, atualização ou exclusão de médicos.
Fluxo de Acesso
O usuário faz login pelo endpoint /auth/login e recebe um token JWT.
Para acessar qualquer rota do MedicoController, o usuário deve incluir o token JWT no cabeçalho da requisição:
bash
Copiar código
Authorization: Bearer <token>
Dependendo do papel do usuário (USER ou ADMIN), o acesso será concedido ou negado conforme as permissões descritas acima.
Considerações de Segurança
Todos os endpoints relacionados a médicos são protegidos por autenticação JWT.
A criação, atualização e exclusão de médicos são restritas a usuários com papel ADMIN.
Usuários com papel USER podem apenas visualizar os médicos (rotas GET), mas não têm permissão para modificá-los.
