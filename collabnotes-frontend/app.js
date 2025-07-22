const API_BASE = 'http://localhost:8080';
let currentUser = null;
let selectedNoteId = null;

window.onload = function () {
  const userId = sessionStorage.getItem('userId');
  if (userId) {
    currentUser = userId;
    showDashboard();
    fetchNotes(userId);
  }
};

function login() {
  const username = document.getElementById('email').value;
  const password = document.getElementById('password').value;
  fetch(`${API_BASE}/login`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ username, password })
  })
    .then(res => res.json())
    .then(data => {
      sessionStorage.setItem('userId', data.id);
      currentUser = data.id;
      showDashboard();
      fetchNotes(data.id);
    });
}

function goToRegister() {
  window.location.href = 'register.html';
}

function showDashboard() {
  document.getElementById('login-container').style.display = 'none';
  document.getElementById('dashboard').style.display = 'block';
}

function fetchNotes(userId) {
  fetch(`${API_BASE}/notes/${userId}`)
    .then(res => res.json())
    .then(data => {
      displayNotes(data.createdNotes, 'user-notes', true);
      displayNotes(data.sharedNotes, 'shared-notes', false);
    });
}

function displayNotes(notes, containerId, isOwner) {
  const container = document.getElementById(containerId);
  container.innerHTML = '';
  notes.forEach(note => {
    const div = document.createElement('div');
    div.className = 'note';
    const accessType = note.accessType || 'EDIT';
    div.innerHTML = `
      <p>${note.content}</p>
      <button onclick="${accessType === 'EDIT' ? `editNote('${note.id}')` : `viewNote('${note.id}')`}">${accessType}</button>
    `;
    container.appendChild(div);
  });
}

function showCreateNoteForm() {
  document.getElementById('note-form').style.display = 'block';
}

function createNote() {
  const content = document.getElementById('new-note-content').value;
  fetch(`${API_BASE}/notes`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ userId: currentUser, content })
  })
    .then(res => res.json())
    .then(() => {
      fetchNotes(currentUser);
      document.getElementById('new-note-content').value = '';
      document.getElementById('note-form').style.display = 'none';
    });
}

function editNote(noteId) {
  selectedNoteId = noteId;
  const newContent = prompt('Edit your note:');
  if (newContent) {
    fetch(`${API_BASE}/notes/${noteId}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ content: newContent })
    }).then(() => fetchNotes(currentUser));
  }
}

function viewNote(noteId) {
  fetch(`${API_BASE}/notes/view/${noteId}`)
    .then(res => res.json())
    .then(note => alert(`Note: ${note.content}`));
}

function shareNote() {
  const email = document.getElementById('share-email').value;
  const access = document.getElementById('access-type').value;
  if (!selectedNoteId) return alert('Select a note to share');

  fetch(`${API_BASE}/share`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ noteId: selectedNoteId, email, access })
  })
    .then(() => alert('Note shared'));
}
function editNote(noteId) {
  selectedNoteId = noteId;
  fetch(`${API_BASE}/notes/view/${noteId}`)
    .then(res => res.json())
    .then(note => {
      document.getElementById('notepad-content').value = note.content;
      document.getElementById('notepad').style.display = 'block';
    });
}

function saveNotepad() {
  const content = document.getElementById('notepad-content').value;
  fetch(`${API_BASE}/notes/${selectedNoteId}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ content })
  }).then(() => {
    fetchNotes(currentUser);
    closeNotepad();
  });
}

function closeNotepad() {
  document.getElementById('notepad').style.display = 'none';
}
