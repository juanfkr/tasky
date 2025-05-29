<template>
    <ScrollPanel style="width: 100%; height: 70dvh">
        <div class="table-container">
            <div class="task" v-for="task in taskList" :key="task.id">
                <p class="task-name">{{ task.name }}</p>
                <div class="task-actions">
                    <Button label="Editar" class="button-delete" @click="openEditModal(task.id)" :loading="loading"
                        severity="warn" />
                    <Button label="Excluir" class="button-delete" @click="openDeleteModal(task.id)" :loading="loading"
                        severity="danger" />
                </div>
            </div>
        </div>

        <Dialog :draggable="false" v-model:visible="deleteModalVisible" modal header="Confirmar exclusão?"
            :style="{ width: '25rem' }">
            <div class="modal-actions-delete">
                <Button type=" button" label="Cancelar" severity="secondary" @click="deleteModalVisible = false"></Button>
                <Button type="button" label="Confirmar" severity="danger" @click="deleteTask"></Button>
            </div>
        </Dialog>

        <Dialog class="modal-actions-edit" :draggable="false" v-model:visible="editModalVisible" modal
            header="Editar tarefa" :style="{ width: '25rem' }">
            <div class="edit-form">
                <InputText id="username" v-model="newTaskName" placeholder="Novo nome" autocomplete="off" />
            </div>
            <div class="modal-edit-buttons">
                <Button type="button" label="Cancelar" severity="secondary" @click="editModalVisible = false"></Button>
                <Button type="button" label="Editar" @click="editTask"></Button>
            </div>
        </Dialog>
    </ScrollPanel>
</template>

<script setup>
import { Button, InputText, Dialog, ScrollPanel } from 'primevue';
import { onMounted, ref } from 'vue';
import axios from 'axios';

const taskList = ref([]);
const loading = ref(false);
const editModalVisible = ref(false);
const deleteModalVisible = ref(false);
const newTaskName = ref('');
const selectedTaskId = ref(null); // Nova variável para armazenar o ID da tarefa selecionada

const fetchTasks = async () => {
    try {
        const res = await axios.get(`https://tasky-brgf.onrender.com/api/tasks`);
        taskList.value = res.data;
    } catch (e) {
        console.error('Error fetching tasks:', e);
    }
};

const openDeleteModal = (id) => {
    selectedTaskId.value = id; // Armazena o ID da tarefa selecionada
    deleteModalVisible.value = true; // Abre o modal de exclusão
};

const openEditModal = (id) => {
    selectedTaskId.value = id; // Armazena o ID da tarefa selecionada
    const task = taskList.value.find(t => t.id === id); // Busca a tarefa pelo ID
    newTaskName.value = task ? task.name : ''; // Preenche o campo com o nome atual
    editModalVisible.value = true; // Abre o modal de edição
};

const deleteTask = async () => {
    try {
        await axios.delete(`https://tasky-brgf.onrender.com/api/tasks/delete/${selectedTaskId.value}`);
        deleteModalVisible.value = false;
        await fetchTasks(); // Atualiza a lista após excluir
    } catch (e) {
        console.error('Error deleting task:', e);
    }
};

const editTask = async () => {
    try {
        const response = await axios.patch(`https://tasky-brgf.onrender.com/api/tasks/update/${selectedTaskId.value}`, {
            name: newTaskName.value
        });
        editModalVisible.value = false;
        newTaskName.value = ''; // Limpa o campo após editar
        await fetchTasks(); // Atualiza a lista após editar
    } catch (e) {
        console.error('Error editing task:', e);
    }
};

onMounted(fetchTasks);
</script>

<style scoped>
.table-container {
    display: flex;
    flex-direction: column;
    gap: 1rem;
}

.task {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #f7f7f7;
    padding: 0 .5em;
    border-radius: 0.5rem;
}

.task-name {
    font-size: 1em;
}

.button-delete {
    padding: 0.5rem 1rem;
    font-size: clamp(0.9rem, 2.5vw, 1rem);
}

@media (max-width: 600px) {
    .task {
        flex-direction: column;
        align-items: flex-start;
        gap: 0.5rem;
    }

    .button-delete {
        width: 100%;
        text-align: center;
    }
}

@media (min-width: 601px) and (max-width: 1024px) {
    .task {
        padding: 0.75rem;
    }
}

.modal-actions-delete {
    display: flex;
    gap: .5em;
    justify-content: end;
}

.modal-actions-edit {
    .edit-form {
        display: flex;
        flex-direction: column;
    }

    .modal-edit-buttons {
        margin-top: 1em;
        display: flex;
        gap: .5em;

        button {
            width: 100%;
        }
    }
}

.task-actions {
    display: flex;
    gap: .5em;
}
</style>