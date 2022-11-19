export default function callBackend(uri, body, headers) {
    if (body) {
        return fetch(uri, {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(body)
        });
    } else {
        return fetch(uri, {
            method: 'GET',
            headers: headers
        });
    }
}