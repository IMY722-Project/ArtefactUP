// utils/session.js

// RFC4122 v4 UUID generator using crypto.getRandomValues if available
function generateUUID() {
  if (window.crypto && window.crypto.getRandomValues) {
    // use crypto API for better randomness
    const arr = new Uint8Array(16);
    window.crypto.getRandomValues(arr);
    // per RFC4122 section 4.4
    arr[6] = (arr[6] & 0x0f) | 0x40;  // version 4
    arr[8] = (arr[8] & 0x3f) | 0x80;  // variant 10
    const hex = [...arr].map(b => b.toString(16).padStart(2, "0"));
    return (
      hex.slice(0, 4).join("") + "-" +
      hex.slice(4, 6).join("") + "-" +
      hex.slice(6, 8).join("") + "-" +
      hex.slice(8,10).join("") + "-" +
      hex.slice(10,16).join("")
    );
  } else {
    // fallback to Math.random-based UUID (less secure)
    return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, c => {
      const r = (Math.random() * 16) | 0;
      const v = c === "x" ? r : (r & 0x3) | 0x8;
      return v.toString(16);
    });
  }
}

export function getSessionId() {
  let sid = localStorage.getItem("sessionId");
  if (!sid) {
    // use native if available, otherwise fallback
    sid =
      typeof crypto.randomUUID === "function"
        ? crypto.randomUUID()
        : generateUUID();
    localStorage.setItem("sessionId", sid);
  }
  return sid;
}
