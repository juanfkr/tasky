<template>
    <div class="task-app">
        <div>
            <div class="header">
                <h1 class="title">Tasky</h1>
                <a href="https://github.com/juanfkr/tasky" target="_blank">
                    <img src=".././public/icons8-github.svg" alt="">
                    Visite o projeto
                </a>
            </div>
            <Form />
        </div>
        <Table />
    </div>
    <Footer />
</template>

<script setup>
import Form from './components/Form.vue';
import Table from './components/Table.vue';
</script>

<style scoped>
.header {
    display: flex;
    align-items: center;
    justify-content: space-between;

    a {
        display: flex;
        align-items: center;
        color: black;
        
        img {
            width: 48px;
            height: 48px;
        }
    }
}

.title {
    user-select: none;
    color: rgb(0, 128, 85);
}

.task-app {
    display: flex;
    flex-direction: column;
    gap: 1em;
    margin: .5em 24em;

    @media (max-width: 1280px) {
        margin: .5em 2em;
    }
}
</style>