# Mapeamento de Riscos Psicossociais - App Android 🧠

![Versão do App](https://img.shields.io/badge/version-1.0.0-blue)
![Licença](https://img.shields.io/badge/license-MIT-green)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9.22-%237F52FF)
![Jetpack Compose](https://img.shields.io/badge/Jetpack_Compose-1.6.8-%234285F4)

Este é o repositório do aplicativo Android para o projeto de Mapeamento de Riscos Psicossociais, desenvolvido para a avaliação e monitoramento da saúde mental de colaboradores em ambiente corporativo, seguindo as diretrizes da norma ISO 45003.

## 📖 Sobre o Projeto

O objetivo deste aplicativo é fornecer uma ferramenta para que empresas possam monitorar o bem-estar de seus colaboradores através de check-ins diários e questionários semanais anônimos. O sistema conta com diferentes níveis de acesso (Roles) para administração da plataforma, administração de empresas e para os usuários finais.

## ✨ Funcionalidades

O aplicativo possui 3 fluxos principais, baseados no tipo de usuário:

#### 👑 Administrador da Plataforma (`PLATFORM_ADMIN`)
- **Autenticação Segura:** Login para obter token JWT e acesso ao painel.
- **Criação de Empresas:** Formulário para cadastrar novas empresas no sistema.
- **Criação de Admins:** Capacidade de cadastrar outros administradores da plataforma.
- **Gestão de Senhas:** Funcionalidade para resetar a senha de qualquer usuário no sistema.

#### 🏢 Administrador da Empresa (`COMPANY_ADMIN`)
- **Autenticação Segura:** Login específico para sua empresa.
- **Criação de Admins da Empresa:** Formulário para cadastrar outros administradores para a mesma empresa.
- **Gestão de Senhas:** Funcionalidade para resetar a senha de usuários da sua empresa.
- **Visualização de Relatórios:** Tela para visualizar os dados consolidados e anônimos dos questionários respondidos pelos colaboradores.

#### 👤 Usuário Final (`USER`)
- **Cadastro por Empresa:** Fluxo de cadastro vinculado a um ID de empresa específico.
- **Autenticação Segura:** Login para acesso à sua área pessoal.
- **Check-in Diário:** Um formulário rápido e interativo com sliders para registrar humor, sono, estresse, etc.
- **Questionário Semanal:** Um questionário completo e anônimo para avaliar os fatores de risco psicossociais, com UI dinâmica e barra de progresso.

## 📸 Screenshots

| Tela de Login | Painel de Admin | Questionário |
| :---: | :---: | :---: |
| <img src="URL_DO_SEU_SCREENSHOT_LOGIN.png" width="250"> | <img src="URL_DO_SEU_SCREENSHOT_ADMIN.png" width="250"> | <img src="URL_DO_SEU_SCREENSHOT_QUESTIONARIO.png" width="250"> |

## 🛠️ Tecnologias Utilizadas

Este projeto foi construído utilizando as tecnologias mais modernas do ecossistema Android:

- **Linguagem:** [Kotlin](https://kotlinlang.org/)
- **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) para uma UI declarativa e moderna.
- **Arquitetura:** MVVM (Model-View-ViewModel) com fluxos de dados unidirecionais.
  - **ViewModel:** Para gerenciar o estado e a lógica da UI.
  - **Repository Pattern:** Para abstrair as fontes de dados.
  - **StateFlow:** Para expor estados da UI de forma reativa e segura.
- **Navegação:** [Navigation Compose](https://developer.android.com/jetpack/compose/navigation) para gerenciar a navegação entre as telas.
- **Networking:** [Retrofit2](https://square.github.io/retrofit/) e [OkHttp](https://square.github.io/okhttp/) (com Logging Interceptor) para comunicação com a API REST.
- **JSON Parsing:** [Gson](https://github.com/google/gson) para serialização e desserialização de objetos.
- **Persistência Local:** [Jetpack DataStore](https://developer.android.com/topic/libraries/architecture/datastore) para armazenar o token de autenticação e dados do usuário de forma assíncrona.

## 🚀 Como Executar o Projeto

Para rodar este projeto, você precisará do Android Studio e de um emulador ou dispositivo físico.

1.  **Clone o repositório:**
    ```bash
    git clone [https://URL-DO-SEU-REPOSITORIO.git](https://URL-DO-SEU-REPOSITORIO.git)
    ```

2.  **Abra no Android Studio:**
    - Abra o Android Studio.
    - Selecione `Open` e navegue até a pasta do projeto clonado.

3.  **Configuração do Backend:**
    > ⚠️ **Importante:** Este aplicativo é um cliente para uma API REST. O servidor backend **deve estar em execução** para que o aplicativo funcione.
    - A URL base da API está configurada no arquivo `data/remote/ApiClient.kt`.
    - O endereço padrão para comunicação com um `localhost` a partir do emulador Android é `http://10.0.2.2:8080/`. Certifique-se de que seu backend está rodando na porta `8080`.

4.  **Execute o App:**
    - Pressione `Run 'app'` (▶️) no Android Studio.

## 🏛️ Estrutura do Projeto

O projeto segue uma estrutura de pacotes clara para separação de responsabilidades:

-   `data`: Contém toda a lógica de dados, dividida em:
    -   `local`: Para persistência de dados no dispositivo (DataStore).
    -   `remote`: Para comunicação com a API (Retrofit, ApiService, DTOs).
    -   `repository`: Implementação do Repository Pattern.
-   `navigation`: Contém o grafo de navegação do app (`AppNavGraph`).
-   `ui`: Contém todos os componentes de UI, divididos em:
    -   `screens`: Pacotes para cada feature (login, user, platformAdmin, etc.).
    -   `theme`: Arquivos de tema do Jetpack Compose (Cores, Tipografia, Tema).

## 👤 Autor

**[SEU NOME AQUI]**

-   LinkedIn: `https://www.linkedin.com/login/pt`
-   GitHub: `https://www.youtube.com/watch?v=9ygf4hkLVnA`

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE.md) para mais detalhes.
