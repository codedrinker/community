package life.majiang.community.dto;

import life.majiang.community.dto.ResultDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class ResultDTOTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  @Rule public final Timeout globalTimeout = new Timeout(10000);

  // Test written by Diffblue Cover.
  @Test
  public void canEqualInputPositiveOutputFalse() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();

    // Act and Assert result
    Assert.assertFalse(resultDTO.canEqual(2));
  }

  // Test written by Diffblue Cover.
  @Test
  public void constructorOutputNotNull() {

    // Act, creating object to test constructor
    final ResultDTO actual = new ResultDTO();

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.getData());
    Assert.assertNull(actual.getCode());
    Assert.assertNull(actual.getMessage());
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsInputNotNullOutputFalse() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();
    resultDTO.setData(0);
    resultDTO.setCode(null);
    resultDTO.setMessage(null);
    final ResultDTO o = new ResultDTO();
    o.setData(null);
    o.setCode(null);
    o.setMessage(null);

    // Act and Assert result
    Assert.assertFalse(resultDTO.equals(o));
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsInputNotNullOutputFalse2() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();
    resultDTO.setData(0);
    resultDTO.setCode(0);
    resultDTO.setMessage(null);
    final ResultDTO o = new ResultDTO();
    o.setData(null);
    o.setCode(null);
    o.setMessage(null);

    // Act and Assert result
    Assert.assertFalse(resultDTO.equals(o));
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsInputNotNullOutputFalse3() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();
    resultDTO.setData(null);
    resultDTO.setCode(null);
    resultDTO.setMessage(null);
    final ResultDTO o = new ResultDTO();
    o.setData(0);
    o.setCode(null);
    o.setMessage(null);

    // Act and Assert result
    Assert.assertFalse(resultDTO.equals(o));
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsInputNotNullOutputFalse4() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();
    resultDTO.setData(null);
    resultDTO.setCode(null);
    resultDTO.setMessage(null);
    final ResultDTO o = new ResultDTO();
    o.setData(0);
    o.setCode(null);
    o.setMessage("");

    // Act and Assert result
    Assert.assertFalse(resultDTO.equals(o));
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsInputNotNullOutputFalse5() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();
    resultDTO.setData(null);
    resultDTO.setCode(0);
    resultDTO.setMessage(null);
    final ResultDTO o = new ResultDTO();
    o.setData(0);
    o.setCode(0);
    o.setMessage("\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");

    // Act and Assert result
    Assert.assertFalse(resultDTO.equals(o));
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsInputNotNullOutputFalse6() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();
    resultDTO.setData(null);
    resultDTO.setCode(0);
    resultDTO.setMessage(
        "\u8000\u8000\u8000\u8000\u8000\u8000\u8000\u8000\u8000\u8000\u8000\u8000\u8000\u8000");
    final ResultDTO o = new ResultDTO();
    o.setData(0);
    o.setCode(0);
    o.setMessage("\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");

    // Act and Assert result
    Assert.assertFalse(resultDTO.equals(o));
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsInputNotNullOutputFalse7() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();
    resultDTO.setData(null);
    resultDTO.setCode(null);
    resultDTO.setMessage(
        "\u8003\u8003\u8003\u8003\u8003\u8003\u8003\u8003\u8003\u8003\u8003\u8003");
    final ResultDTO o = new ResultDTO();
    o.setData(null);
    o.setCode(0);
    o.setMessage("\u8003\u8003\u8003\u8003\u8003\u8003\u8003\u8003\u8003\u8003\u8003\u8003");

    // Act and Assert result
    Assert.assertFalse(resultDTO.equals(o));
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsInputNotNullOutputTrue() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();
    final ResultDTO o = new ResultDTO();

    // Act and Assert result
    Assert.assertTrue(resultDTO.equals(o));
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsInputNotNullOutputTrue2() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();
    resultDTO.setData(0);
    resultDTO.setCode(null);
    resultDTO.setMessage(null);
    final ResultDTO o = new ResultDTO();
    o.setData(0);
    o.setCode(null);
    o.setMessage(null);

    // Act and Assert result
    Assert.assertTrue(resultDTO.equals(o));
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsInputNotNullOutputTrue3() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();
    resultDTO.setData(null);
    resultDTO.setCode(0);
    resultDTO.setMessage(
        "\u8003\u8003\u8003\u8003\u8003\u8003\u8003\u8003\u8003\u8003\u8003\u8002");
    final ResultDTO o = new ResultDTO();
    o.setData(null);
    o.setCode(0);
    o.setMessage("\u8003\u8003\u8003\u8003\u8003\u8003\u8003\u8003\u8003\u8003\u8003\u8002");

    // Act and Assert result
    Assert.assertTrue(resultDTO.equals(o));
  }

  // Test written by Diffblue Cover.
  @Test
  public void equalsInputNullOutputFalse() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();
    resultDTO.setData(0);
    resultDTO.setCode(0);
    resultDTO.setMessage(null);

    // Act and Assert result
    Assert.assertFalse(resultDTO.equals(null));
  }

  // Test written by Diffblue Cover.
  @Test
  public void errorOfInputNotification_not_foundOutputNotNull() {

    // Arrange
    final CustomizeErrorCode errorCode = CustomizeErrorCode.NOTIFICATION_NOT_FOUND;

    // Act
    final ResultDTO actual = ResultDTO.errorOf(errorCode);

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.getData());
    Assert.assertEquals(new Integer(2009), actual.getCode());
    Assert.assertEquals("\u6d88\u606f\u83ab\u975e\u662f\u4e0d\u7ffc\u800c\u98de\u4e86\uff1f",
                        actual.getMessage());
  }

  // Test written by Diffblue Cover.
  @Test
  public void errorOfInputPositiveNotNullOutputNotNull() {

    // Act
    final ResultDTO actual = ResultDTO.errorOf(2, "/");

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.getData());
    Assert.assertEquals(new Integer(2), actual.getCode());
    Assert.assertEquals("/", actual.getMessage());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getCodeOutputNull() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();

    // Act and Assert result
    Assert.assertNull(resultDTO.getCode());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getDataOutputNull() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();

    // Act and Assert result
    Assert.assertNull(resultDTO.getData());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getMessageOutputNull() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();

    // Act and Assert result
    Assert.assertNull(resultDTO.getMessage());
  }

  // Test written by Diffblue Cover.
  @Test
  public void hashCodeOutputPositive() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();

    // Act and Assert result
    Assert.assertEquals(357_642, resultDTO.hashCode());
  }

  // Test written by Diffblue Cover.
  @Test
  public void hashCodeOutputZero() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();
    resultDTO.setData(null);
    resultDTO.setCode(1_167_204_497);
    resultDTO.setMessage(null);

    // Act and Assert result
    Assert.assertEquals(0, resultDTO.hashCode());
  }

  // Test written by Diffblue Cover.
  @Test
  public void hashCodeOutputZero2() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();
    resultDTO.setData(43);
    resultDTO.setCode(1_167_204_497);
    resultDTO.setMessage(null);

    // Act and Assert result
    Assert.assertEquals(0, resultDTO.hashCode());
  }

  // Test written by Diffblue Cover.
  @Test
  public void hashCodeOutputZero3() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();
    resultDTO.setData(43);
    resultDTO.setCode(1_167_204_497);
    resultDTO.setMessage("\u0000+");

    // Act and Assert result
    Assert.assertEquals(0, resultDTO.hashCode());
  }

  // Test written by Diffblue Cover.
  @Test
  public void okOfInputPositiveOutputNotNull() {

    // Act
    final ResultDTO actual = ResultDTO.okOf(2);

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertEquals(2, actual.getData());
    Assert.assertEquals(new Integer(200), actual.getCode());
    Assert.assertEquals("\u8bf7\u6c42\u6210\u529f", actual.getMessage());
  }

  // Test written by Diffblue Cover.
  @Test
  public void okOfOutputNotNull() {

    // Act
    final ResultDTO actual = ResultDTO.okOf();

    // Assert result
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.getData());
    Assert.assertEquals(new Integer(200), actual.getCode());
    Assert.assertEquals("\u8bf7\u6c42\u6210\u529f", actual.getMessage());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setCodeInputPositiveOutputVoid() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();

    // Act
    resultDTO.setCode(2);

    // Assert side effects
    Assert.assertEquals(new Integer(2), resultDTO.getCode());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setDataInputPositiveOutputVoid() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();

    // Act
    resultDTO.setData(2);

    // Assert side effects
    Assert.assertEquals(2, resultDTO.getData());
  }

  // Test written by Diffblue Cover.
  @Test
  public void setMessageInputNotNullOutputVoid() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();

    // Act
    resultDTO.setMessage("/");

    // Assert side effects
    Assert.assertEquals("/", resultDTO.getMessage());
  }

  // Test written by Diffblue Cover.
  @Test
  public void toStringOutputNotNull() {

    // Arrange
    final ResultDTO resultDTO = new ResultDTO();
    resultDTO.setData(-63);
    resultDTO.setCode(null);
    resultDTO.setMessage("");

    // Act and Assert result
    Assert.assertEquals("ResultDTO(code=null, message=, data=-63)", resultDTO.toString());
  }
}
