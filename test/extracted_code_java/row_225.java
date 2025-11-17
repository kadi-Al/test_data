html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Maintenance Mode Toggle</title>
</head>
<body>
    <div id="app">
        <!-- Your public path content here -->
        <h1>Welcome to our website!</h1>
        <p>This is a public page where you can see some information.</p>
    </div>

    <script>
        // Function to check if the maintenance mode query parameter is set
        function isMaintenanceMode() {
            const urlParams = new URLSearchParams(window.location.search);
            return urlParams.has('maintenance_mode') && urlParams.get('maintenance_mode') === 'true';
        }

        // Set maintenance mode based on query parameter
        function setMaintenanceMode() {
            if (isMaintenanceMode()) {
                document.body.innerHTML = "<h1>Site under maintenance</h1><p>We'll be back shortly.</p>";
            } else {
                document.body.innerHTML = "<div id='app'><h1>Welcome to our website!</h1><p>This is a public page where you can see some information.</p></div>";
            }
        }

        // Call the function when the DOM is fully loaded
        window.addEventListener('load', setMaintenanceMode);
    </script>
</body>
</html>
jsx
import React from 'react';
import { useLocation } from 'react-router-dom';

function PublicPage() {
    const location = useLocation();
    const urlParams = new URLSearchParams(location.search);
    const isMaintenanceMode = urlParams.has('maintenance_mode') && urlParams.get('maintenance_mode') === 'true';

    return (
        <div>
            {isMaintenanceMode ? (
                <h1>Site under maintenance</h1>
            ) : (
                <h1>Welcome to our website!</h1>
            )}
            <p>{isMaintenanceMode ? 'We\'ll be back shortly.' : 'This is a public page where you can see some information.'}</p>
        </div>
    );
}

export default PublicPage;
script
import { createApp } from 'vue';
import { createRouter, createWebHistory } from 'vue-router';

const routes = [
    { path: '/', component: PublicPage },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

router.beforeEach((to, from, next) => {
    const urlParams = new URLSearchParams(window.location.search);
    const isMaintenanceMode = urlParams.has('maintenance_mode') && urlParams.get('maintenance_mode') === 'true';

    if (isMaintenanceMode) {
        document.body.innerHTML = "<h1>Site under maintenance</h1><p>We'll be back shortly.</p>";
    } else {
        document.body.innerHTML = "<div id='app'><h1>Welcome to our website!</h1><p>This is a public page where you can see some information.</p></div>";
    }

    next();
});

const app = createApp(App);
app.use(router);
app.mount('#app');