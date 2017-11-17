int OUTPUT_2 = 2;
int OUTPUT_3 = 3;
int OUTPUT_4 = 4;

int INPUT_11 = 11;
int INPUT_12 = 12;
int LIGHT_13 = 13;


void setup() {
  // put your setup code here, to run once:
  pinMode(OUTPUT_2, OUTPUT);
  pinMode(OUTPUT_3, OUTPUT);
  pinMode(OUTPUT_4, OUTPUT);

  pinMode(INPUT_11, INPUT);
  pinMode(INPUT_12, INPUT);
  pinMode(LIGHT_13, INPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  // connect a limit switch to pin 11, and connect pin 2 to pin 13. This reads if the limit switch is high or low.
  // if the limit switch is pressed the light will turn off and vices versa. This is to simulate what will be done with the roboRio.
  if(digitalRead(INPUT_11 == HIGH))digitalWrite(OUTPUT_2, LOW); 
  else digitalWrite(OUTPUT_2,HIGH);
  digitalRead(LIGHT_13);// checks the state of pin 2
}
