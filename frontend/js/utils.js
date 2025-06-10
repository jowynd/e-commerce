//load resources
async function loadComponents() {

    const headerElement = document.querySelector("header");
    const footerElement = document.querySelector("footer");

    if (headerElement) {

        const headerResponse = await fetch("/frontend/components/header.html");
        const headerHtml = await headerResponse.text();

        headerElement.innerHTML = headerHtml;
    }

    if (footerElement) {

        const footerResponse = await fetch("/frontend/components/footer.html");
        const footerHtml = await footerResponse.text();

        footerElement.innerHTML = footerHtml;
    }
}
document.addEventListener("DOMContentLoaded", loadComponents);
