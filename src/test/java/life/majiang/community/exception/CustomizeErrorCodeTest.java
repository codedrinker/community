package life.majiang.community.exception;

import life.majiang.community.exception.CustomizeErrorCode;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class CustomizeErrorCodeTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  @Rule public final Timeout globalTimeout = new Timeout(10000);

  // Test written by Diffblue Cover.
  @Test
  public void getCodeOutputPositive() {

    // Arrange
    final CustomizeErrorCode customizeErrorCode = CustomizeErrorCode.NOTIFICATION_NOT_FOUND;

    // Act and Assert result
    Assert.assertEquals(new Integer(2009), customizeErrorCode.getCode());
  }

  // Test written by Diffblue Cover.
  @Test
  public void getMessageOutputNotNull() {

    // Arrange
    final CustomizeErrorCode customizeErrorCode = CustomizeErrorCode.NOTIFICATION_NOT_FOUND;

    // Act and Assert result
    Assert.assertEquals("\u6d88\u606f\u83ab\u975e\u662f\u4e0d\u7ffc\u800c\u98de\u4e86\uff1f",
                        customizeErrorCode.getMessage());
  }

  // Test written by Diffblue Cover.
  @Test
  public void valueOfInputNotNullOutputIllegalArgumentException() {

    // Act
    thrown.expect(IllegalArgumentException.class);
    CustomizeErrorCode.valueOf("/");

    // The method is not expected to return due to exception thrown
  }
}
