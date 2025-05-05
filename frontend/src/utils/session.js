export function getSessionId() {
    // check localStorage first
    let sid = localStorage.getItem("sessionId");
    if (!sid) {
      // modern browsers support crypto.randomUUID()
      sid = crypto.randomUUID();
      localStorage.setItem("sessionId", sid);
    }
    return sid;
  }