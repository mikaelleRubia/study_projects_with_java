package com.devsuperior.aula;

import com.devsuperior.aula.entities.Order;
import com.devsuperior.aula.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class AulaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AulaApplication.class, args);
	}

	@Autowired
	private OrderService orderService;

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);

		System.out.println("Digite os dados do pedido:");
		System.out.print("Código: ");
		int code = sc.nextInt();

		System.out.print("Valor básico: ");
		double basic = sc.nextDouble();

		System.out.print("Desconto (%): ");
		double discount = sc.nextDouble();

		Order order = new Order(code, basic, discount);

		double total = orderService.total(order);

		System.out.printf("Pedido código: %d # TOTAL: R$ %.2f%n", order.getCode(), total);

		sc.close();
	}

}
