document.addEventListener('DOMContentLoaded', () => {
    const container = document.getElementById('tarot-container');
    const btn = document.getElementById('generate-btn');

    btn.addEventListener('click', async () => {
        container.innerHTML = '';  // clear previous card

        try {
            const res = await fetch('/api/tarot');
            const card = await res.json();

            const cardEl = document.createElement('div');
            cardEl.className = 'tarot-card';

            const titleEl = document.createElement('div');
            titleEl.className = 'card-title';
            titleEl.textContent = card.name;

            const meaningEl = document.createElement('div');
            meaningEl.className = 'card-meaning';
            meaningEl.textContent = card.meaning;

            cardEl.appendChild(titleEl);
            cardEl.appendChild(meaningEl);
            container.appendChild(cardEl);

        } catch (error) {
            container.textContent = 'Failed to load card. Please try again.';
            console.error(error);
        }
    });
});

