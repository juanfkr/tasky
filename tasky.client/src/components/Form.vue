<template>
    <form class="form-container" @submit.prevent="createTask">
        <div class="form-label">
            <InputText class="input" v-model="taskName" placeholder="Nome da tarefa" />
            <Button label="Criar" type="submit" class="button" :loading="loading" />
        </div>
        <p v-if="taskResponse" :class="taskResponseStyle">{{ taskResponse }}</p>
    </form>
</template>

<script setup>
import { ref } from 'vue';
import { InputText, Button } from 'primevue';
import axios from 'axios';

const taskName = ref(''); // Usando ref, já que defineModel não parece necessário
const taskResponse = ref(''); // Armazena mensagens de sucesso ou erro
const taskResponseStyle = ref(''); // Estiliza a mensagem de acordo com o resultado (sucesso/erro);
const loading = ref(false);

async function createTask() {
    loading.value = true;
    try {
        const response = await axios.post(`https://tasky-brgf.onrender.com/api/tasks/post`, {
            name: taskName.value,
        });
        // Sucesso: exibe a mensagem e recarrega a página manualmente
        taskResponseStyle.value = "task-response-correct"
        taskName.value = '';
        window.location.reload();
    } catch (e) {
        loading.value = false;
        if (e.response) {
            taskResponseStyle.value = "task-response-error";
            taskResponse.value = e.response.data.message;
        }
    }
}
</script>

<style scoped>
.form-container {
    .form-label {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
        gap: 1em;
    }

    .task-response-error {
        margin: 0.5rem 0;
        color: rgb(255, 20, 59);
    }

    .task-response-correct {
        margin: 0.5rem 0;
        color: rgb(17, 216, 60);
    }

    .input {
        flex: 4;
    }

    .button {
        flex: 1;
    }
}
</style>