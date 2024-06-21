package controller.model.cryptor;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public final class Cryptor 
{
    private final SecretKey secretKey;

    public Cryptor(final String keyParameter)
    {
        SecretKey tempSecretKey = null;
        try 
        {
            final byte[] salt = "8iYlfHIn6pOiy4kRMLhsv1G9dE2Ez1xtdXfD3PtnvzSB86ED7lWn6rZOlS6wVofF".getBytes();
            final KeySpec keySpec = new PBEKeySpec(keyParameter.toCharArray(), salt, 10000, 256);
            tempSecretKey = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(keySpec).getEncoded(), "AES");
        } 
        catch (final InvalidKeySpecException | NoSuchAlgorithmException exception) 
        {
            System.exit(0);
        }
        this.secretKey = tempSecretKey;
    }

    public final String encrypt(final String plainTextParameter) 
    {
        try
        {
            final Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
            final byte[] encryptedBytes = cipher.doFinal(plainTextParameter.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        }
        catch (final InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException exception)
        {
            return null;
        }
    }

    public final String decrypt(final String encryptedTextParameter) 
    {
        try
        {
            final Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, this.secretKey);
            final byte[] encryptedBytes = Base64.getDecoder().decode(encryptedTextParameter);
            final byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        }
        catch (final InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException exception)
        {
            return null;
        }
    }
}
