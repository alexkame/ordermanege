/**
 * Created by luyu on 2015/5/28.
 */
var encryptKey = '1234567890123456';

var aesEncrypt = function(message){
    message = message.toString();
    var keyHex = CryptoJS.enc.Utf8.parse(encryptKey);
    var encrypted = CryptoJS.DES.encrypt(message, keyHex, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    return encrypted.toString();
};
var aesDecrypt = function(encrypted){
    var keyHex = CryptoJS.enc.Utf8.parse(encryptKey);
    var decrypted =  CryptoJS.DES.decrypt(encrypted, keyHex, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    return decrypted.toString(CryptoJS.enc.Utf8);
}

