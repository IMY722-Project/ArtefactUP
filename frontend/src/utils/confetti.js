export function startConfettiAnimation(className) {
    console.log("confetti should be here")
    const containers = document.getElementsByClassName(className);

    if (!containers.length) {
        console.warn("Confetti container with class not found");
        return;
    }

    const wrapper = containers[0]; // Use the first matching element
    for (let i = 0; i < 190; i++) {
        const confetti = document.createElement("div");
        const fallDistance = 20 + Math.random() * 80; // Between 20vh and 100vh
        confetti.style.setProperty('--fall-distance', `${fallDistance}vh`);

        confetti.classList.add("confetti");
        confetti.style.position = "absolute";
        confetti.style.left = Math.random() * 100 + "vw";
        confetti.style.backgroundColor =
            ["#de2e5d", "#e35d58", "#e78e40", "#a2d72f", "#62bfd1", "#5e8fcf"][
            Math.floor(Math.random() * 6)
            ];
        confetti.style.animationDuration = 2 + Math.random() * 2 + "s";
        wrapper.appendChild(confetti);

        setTimeout(() => {
            confetti.remove();
        }, 4000);
    }
}
